package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.sys.bean.model.VerifyComponent;
import com.lee.hof.sys.mapper.VerifyComponentMapper;
import com.lee.hof.sys.service.VerifyComponentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangle
 * @since 2021-09-11
 */
@Service
public class VerifyComponentServiceImpl extends ServiceImpl<VerifyComponentMapper, VerifyComponent> implements VerifyComponentService {

    @Resource
    VerifyComponentMapper verifyComponentMapper;



    @Override
    public String addComponent(MultipartFile file, String componentId) {
        return null;
    }


}
