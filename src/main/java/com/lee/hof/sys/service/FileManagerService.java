package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.model.FileManager;
import com.lee.hof.sys.bean.vo.FileUploadBean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangle
 * @since 2021-09-11
 */
public interface FileManagerService extends IService<FileManager> {
    FileUploadBean uploadFile(MultipartFile file) throws Exception;
    FileUploadBean uploadFileNew(MultipartFile file) throws Exception;

//    void download(String fileId, HttpServletResponse response) throws FileNotFoundException;

    void downloadNew(String fileId, HttpServletResponse response) throws IOException;

    void clean();
}
