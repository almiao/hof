package com.lee.hof.sys.controller;

import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.vo.FileUploadBean;
import com.lee.hof.sys.service.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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


    @PostMapping(value = "/upload",headers = "content-type=multipart/form-data")
    public BaseResponse<FileUploadBean> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

       return BaseResponse.success(fileService.uploadFile(file));

    }

    @GetMapping("/download")
    @ResponseBody
    public void downloadFile(@RequestParam String fileId, HttpServletResponse response) throws IOException{

        fileService.download(fileId,response);
    }

    @PostMapping("/upload/multi")
    @ResponseBody
    public BaseResponse<String> pic(MultipartFile[] files, HttpSession session) throws Exception {
        List<String> result = new LinkedList<>();
        for(MultipartFile file:files){
            FileUploadBean fileUploadBean =   fileService.uploadFile(file);
            result.add(fileUploadBean.getId());
        }
        return BaseResponse.success(String.join(",",result));
    }

}
