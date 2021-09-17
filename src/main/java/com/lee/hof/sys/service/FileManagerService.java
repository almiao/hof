package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.model.FileManager;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangle
 * @since 2021-09-11
 */
public interface FileManagerService extends IService<FileManager> {
    public String uploadFile(MultipartFile file) throws Exception;

    void download(String fileId, HttpServletResponse response) throws FileNotFoundException;
}
