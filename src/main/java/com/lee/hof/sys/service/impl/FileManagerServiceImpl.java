package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.common.exception.HofException;
import com.lee.hof.common.upload.service.local.LocalStorageService;
import com.lee.hof.sys.bean.model.FileManager;
import com.lee.hof.sys.bean.vo.FileUploadBean;
import com.lee.hof.sys.mapper.FileManagerMapper;
import com.lee.hof.sys.service.FileManagerService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
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
//        UploadedFileBean uploadedFileBean = localStorageService.uploadFile(file);

        // 参数列表
//        UploadedFileBean uploadedFileBean = new UploadedFileBean();


        String uuid = UUID.randomUUID().toString();

        FileManager fileManager = new FileManager();
//        BeanUtils.copyProperties(uploadedFileBean, fileManager);
        fileManager.setName(file.getOriginalFilename());
        fileManager.setUuid(uuid);
        fileManager.setContent(bos.toByteArray());

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

        if(fileManager==null || fileManager.getContent() == null){
            throw new HofException("未找到文件");
        }
        System.out.println("下载文件文件:" + fileManager.getName());
        // 参数列表
//        try (InputStream inputStream = localStorageService.getFile(fileManager.getFileId())) {
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
}
