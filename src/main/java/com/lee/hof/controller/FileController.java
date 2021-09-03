package com.lee.hof.controller;

import com.lee.hof.service.impl.FileService;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @author tangle
 * @description 管理饭店相关接口
 * @date 2021/9/3
 */

@RequestMapping("file")
public class FileController {

    @Autowired
    FileService fileService;


    @PostMapping("/upload")
    public void uploadFile(MultipartFile file) throws Exception {

        fileService.uploadFile(file);

    }


    @SneakyThrows
    @GetMapping("/download")
    @ResponseBody
    public void downloadFile(@RequestParam String fileName, HttpServletResponse response) throws IOException{

        System.out.println("下载文件");
        String path = System.getProperty("user.dir")+ File.separator+fileName;
        File tempFile = new File(path);
        InputStream stream = new FileInputStream(tempFile);
        String encodedFilename = URLEncoder.encode(tempFile.getName(), "UTF-8");
        response.setHeader("Content-disposition", String.format("attachment;filename=%s;filename*=UTF-8''%s", encodedFilename, encodedFilename));
        response.setContentType("image/png");
        IOUtils.copy(stream, response.getOutputStream());
    }



}
