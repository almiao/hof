package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.common.exception.HofException;
import com.lee.hof.common.upload.UploadedFileBean;
import com.lee.hof.common.upload.service.local.LocalStorageService;
import com.lee.hof.sys.bean.model.FileManager;
import com.lee.hof.sys.bean.vo.FileUploadBean;
import com.lee.hof.sys.mapper.FileManagerMapper;
import com.lee.hof.sys.service.FileManagerService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        fileManager.setContent(bos.toByteArray());
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
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = -1;
        while((len = input.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        // 参数列

        UploadedFileBean uploadedFileBean = localStorageService.uploadFile(file);


        FileManager fileManager = new FileManager();
        fileManager.setName(file.getOriginalFilename());
        fileManager.setContent(bos.toByteArray());
        fileManager.setFullPath(uploadedFileBean.getFullPath());
        fileManager.setUuid(uploadedFileBean.getFileId());
        fileManager.setProvider(uploadedFileBean.getProvider());

//        // 参数列表

        this.baseMapper.insert(fileManager);

        FileUploadBean fileBean = new FileUploadBean();

        fileBean.setId(fileManager.getUuid());
        fileBean.setName(fileManager.getName());
        fileBean.setUrl(fileManager.getFullPath());

        //请求接口
        return fileBean;
    }


    public void download(String uuid, HttpServletResponse response) {

        FileManager fileManager = this.baseMapper.getByUuid(uuid);

        if(fileManager==null){
            throw new HofException("未找到文件");
        }
        System.out.println("下载文件文件:" + fileManager.getName());
        // 参数列表
        try (InputStream inputStream =  new ByteArrayInputStream(fileManager.getContent())){
            String encoderFileName = URLEncoder.encode(fileManager.getName(), "UTF-8");
            response.setHeader("Content-deposition", String.format("attachment;filename=%s;filename*=UTF-8''%s", encoderFileName, encoderFileName));
            response.setContentType(fileManager.getType());
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new HofException("文件读取错误");
        }
    }


    public void downloadNew(String uuid, HttpServletResponse response) {
        FileManager fileManager = this.baseMapper.getByUuid(uuid);
        if (fileManager == null ) {
            throw new HofException("未找到文件");
        }
        // 参数列表
        try {
            InputStream inputStream = null;
            if(fileManager.getProvider() == null){
                inputStream =  new ByteArrayInputStream(fileManager.getContent());
            }else{
                inputStream = localStorageService.getFile(fileManager.getFullPath());
            }
            String encoderFileName = URLEncoder.encode(fileManager.getName(), "UTF-8");
            response.setHeader("Content-deposition", String.format("attachment;filename=%s;filename*=UTF-8''%s", encoderFileName, encoderFileName));
            response.setContentType(fileManager.getType());
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new HofException("文件读取错误");
        }
    }


    public void clean() {
        List<FileManager> fileManagers = this.baseMapper.selectList(new QueryWrapper<>());
        if (fileManagers == null ) {
            throw new HofException("未找到文件");
        }
        for(FileManager fileManager:fileManagers){
            // 参数列表
            try {
                InputStream inputStream = null;
                if(fileManager.getProvider() == null){
                    inputStream =  new ByteArrayInputStream(fileManager.getContent());
                }else{
                    inputStream = localStorageService.getFile(fileManager.getFullPath());
                }
                BufferedImage imageIO = ImageIO.read(inputStream);
                fileManager.setWidth(imageIO.getWidth());
                fileManager.setHeight(imageIO.getHeight());
                baseMapper.updateById(fileManager);
            } catch (IOException e) {
                e.printStackTrace();
                throw new HofException("文件读取错误");
            }
        }

    }



}
