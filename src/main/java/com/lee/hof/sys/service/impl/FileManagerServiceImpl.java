package com.lee.hof.sys.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.afkbrb.avatar.Avatar;
import com.lee.hof.common.exception.HofException;
import com.lee.hof.common.upload.UploadedFileBean;
import com.lee.hof.common.upload.service.local.LocalStorageService;
import com.lee.hof.sys.bean.model.FileManager;
import com.lee.hof.sys.bean.vo.FileUploadBean;
import com.lee.hof.sys.mapper.FileManagerMapper;
import com.lee.hof.sys.service.FileManagerService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangle
 * @since 2021-09-11
 */
@Service
public class FileManagerServiceImpl extends ServiceImpl<FileManagerMapper, FileManager> implements FileManagerService {

    @Autowired
    LocalStorageService localStorageService;


    public FileUploadBean uploadFile(MultipartFile file) throws Exception {
        System.out.println("上传文件:" + file.getOriginalFilename());

        InputStream input = file.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = -1;
        while((len = input.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        // 参数列表
//        UploadedFileBean uploadedFileBean = new UploadedFileBean();


        String uuid = UUID.randomUUID().toString().substring(0,8);

        FileManager fileManager = new FileManager();
//        BeanUtils.copyProperties(uploadedFileBean, fileManager);
        fileManager.setName(file.getOriginalFilename());
        fileManager.setUuid(uuid);
        fileManager.setFullPath("/" + uuid +"/" +fileManager.getName());
//        // 参数列表
//        UploadedFileBean uploadedFileBean = localStorageService.uploadFile(file);

        this.baseMapper.insert(fileManager);

        FileUploadBean fileBean = new FileUploadBean();

        fileBean.setId(fileManager.getUuid());
        fileBean.setName(fileManager.getName());
        fileBean.setUrl(fileManager.getFullPath());

        //请求接口
        return fileBean;
    }


    public FileUploadBean uploadFileNew(MultipartFile file) throws Exception {

        InputStream input = file.getInputStream();

        BufferedImage bufferedImage = ImageIO.read(input);

        // 参数列

        UploadedFileBean uploadedFileBean = localStorageService.uploadFile(file);


        FileManager fileManager = new FileManager();
        fileManager.setName(file.getOriginalFilename());
        fileManager.setFullPath(uploadedFileBean.getFullPath());
        fileManager.setUuid(uploadedFileBean.getFileId());
        fileManager.setProvider(uploadedFileBean.getProvider());
        fileManager.setWidth(bufferedImage.getWidth());
        fileManager.setHeight(bufferedImage.getHeight());
        fileManager.setFileId(uploadedFileBean.getFileId());
//        // 参数列表

        this.baseMapper.insert(fileManager);

        FileUploadBean fileBean = new FileUploadBean();

        fileBean.setId(fileManager.getUuid());
        fileBean.setName(fileManager.getName());
        fileBean.setUrl(fileManager.getFullPath());

        //请求接口
        return fileBean;
    }


//    public void download(String uuid, HttpServletResponse response) {
//
//        FileManager fileManager = this.baseMapper.getByUuid(uuid);
//
//        if(fileManager==null){
//            throw new HofException("未找到文件");
//        }
//        System.out.println("下载文件文件:" + fileManager.getName());
//        // 参数列表
//        try (InputStream inputStream =  new ByteArrayInputStream(fileManager.getContent())){
//            String encoderFileName = URLEncoder.encode(fileManager.getName(), "UTF-8");
//            response.setHeader("Content-deposition", String.format("attachment;filename=%s;filename*=UTF-8''%s", encoderFileName, encoderFileName));
//            response.setContentType(fileManager.getType());
//            IOUtils.copy(inputStream, response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new HofException("文件读取错误");
//        }
//    }

    @Resource
    FileManagerMapper fileManagerMapper;

    public void downloadNew(String uuid, HttpServletResponse response) throws IOException {
        FileManager fileManager = this.baseMapper.getByUuid(uuid);
        if (fileManager == null ) {
            String fileId = uuid;
            Avatar avatar = new Avatar();
            BufferedImage bufferedImage =  avatar.generateAndGetAvatar();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", os);
            String localFilename = localStorageService.getLocalStoragePath() + "/"+uuid +".png";
            FileUtils.forceMkdir(new File(localFilename).getParentFile());
            org.apache.commons.io.IOUtils.copy(new ByteArrayInputStream(os.toByteArray()), new FileOutputStream(localFilename));
            fileManager = new FileManager();
            fileManager.setName(uuid +".png");
            fileManager.setFullPath(uuid +".png");
            fileManager.setUuid(fileId);
            fileManager.setFileId(uuid);
            fileManager.setProvider("local");
            fileManagerMapper.insert(fileManager);
        }

        // 参数列表
        try {
            InputStream inputStream = null;
            inputStream = localStorageService.getFile(fileManager.getFullPath());
            String encoderFileName = URLEncoder.encode(fileManager.getName(), "UTF-8");
            response.setHeader("Content-deposition", String.format("attachment;filename=%s;filename*=UTF-8''%s", encoderFileName, encoderFileName));
            response.setContentType(fileManager.getType());
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            logger.info("文件读取错误:", e);
            throw new HofException("文件读取错误:"+fileManager.getUuid());
        }
    }


    private final static Logger logger = LoggerFactory.getLogger(FileManagerServiceImpl.class);
    public void clean() {
        List<FileManager> fileManagers = this.baseMapper.selectList(new QueryWrapper<>());
        logger.warn(JSONUtil.toJsonStr(fileManagers));
        for(FileManager fileManager:fileManagers){
            // 参数列表
            try {
                logger.warn("error:{}"+fileManager.getFullPath());
                InputStream inputStream = localStorageService.getFile(fileManager.getFullPath());
                BufferedImage imageIO = ImageIO.read(inputStream);
                fileManager.setWidth(imageIO.getWidth());
                fileManager.setHeight(imageIO.getHeight());
                logger.warn(JSONUtil.toJsonStr(fileManager));
                baseMapper.updateById(fileManager);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("error:", e);
            }
        }

    }



}
