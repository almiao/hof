package com.lee.hof.sys.service.impl;

import com.lee.hof.common.upload.UploadedFileBean;
import com.lee.hof.common.upload.service.local.LocalStorageService;
import com.lee.hof.sys.bean.model.FileManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author tangle
 * @description
 * @date 2021/9/3
 */
@Service
public class FileService {


    @Autowired
    LocalStorageService localStorageService;


    public String uploadFile(MultipartFile file) throws Exception {
        System.out.println("上传文件:" + file.getOriginalFilename());
        // 参数列表
        UploadedFileBean uploadedFileBean =  localStorageService.uploadFile(file);

        FileManager fileManager = new FileManager();
        BeanUtils.copyProperties(uploadedFileBean,fileManager);

//        fileManager



        //请求接口
        return "https://www.tangle.ink:9200/file/download?fileName="+file.getOriginalFilename();
    }



//    public void saveFile(InputStream inputStream, String fileName) {
//        OutputStream os = null;
//        try {
//            String path = System.getProperty("user.dir");
//            // 2、保存到临时文件
//            // 1K的数据缓冲
//            byte[] bs = new byte[1024];
//            // 读取到的数据长度
//            int len;
//            // 输出的文件流保存到本地文件
//
//            File tempFile = new File(path);
//            if (!tempFile.exists()) {
//                boolean success = tempFile.mkdir();
//                if(!success){
//                    throw new Exception("未创建文件");
//                }
//            }
//            os = new FileOutputStream(tempFile+File.separator+fileName);
//            // 开始读取
//            while ((len = inputStream.read(bs)) != -1) {
//                os.write(bs, 0, len);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 完毕，关闭所有链接
//            try {
//                os.close();
//                inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
