package com.lee.hof.sys.service.impl;

import com.lee.hof.common.upload.UploadedFileBean;
import com.lee.hof.common.upload.service.local.LocalStorageService;
import com.lee.hof.sys.bean.model.FileManager;
import com.lee.hof.sys.mapper.FileManagerMapper;
import com.lee.hof.sys.service.FileManagerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;

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


    public String uploadFile(MultipartFile file) throws Exception {
        System.out.println("上传文件:" + file.getOriginalFilename());
        // 参数列表
        UploadedFileBean uploadedFileBean =  localStorageService.uploadFile(file);

        FileManager fileManager = new FileManager();
        BeanUtils.copyProperties(uploadedFileBean,fileManager);

//        fileManager

        save(fileManager);

        //请求接口
        return uploadedFileBean.getFileId();
    }




    public void download(String fileId , HttpServletResponse response) throws Exception {

        FileManager fileManager = this.baseMapper.getByFileId(fileId);


        System.out.println("下载文件文件:" + fileId);
        // 参数列表
        try(InputStream inputStream  =  localStorageService.getFile(fileId)){
            String encoderFileName = URLEncoder.encode(fileManager.getName(),"UTF-8");
            response.setHeader("Content-deposition",String.format("attachment;filename=%s;filename*=UTF-8''%s",encoderFileName,encoderFileName));
            response.setContentType(fileManager.getType());
            IOUtils.copy(inputStream,response.getOutputStream())
        }




}
