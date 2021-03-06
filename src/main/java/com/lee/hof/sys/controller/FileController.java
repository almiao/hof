package com.lee.hof.sys.controller;

import com.lee.hof.sys.bean.vo.FileUploadBean;
import com.lee.hof.sys.service.FileManagerService;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileManagerService fileService;


    @PostMapping("/upload")
    public ResponseEntity<FileUploadBean> uploadFile(MultipartFile file) throws Exception {

       return ResponseEntity.ok(fileService.uploadFile(file));

    }

    @GetMapping("/download")
    @ResponseBody
    public void downloadFile(@RequestParam String fileId, HttpServletResponse response) throws IOException{

        fileService.download(fileId,response);
    }



}
