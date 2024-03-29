package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.model.VerifyComponent;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
   * @author
 * @since 2021-09-11
 */
public interface VerifyComponentService extends IService<VerifyComponent> {

    String addComponent(MultipartFile file, String componentId) throws IOException;
}
