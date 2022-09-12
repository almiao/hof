package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.sys.bean.model.UserComponent;
import com.lee.hof.sys.mapper.UserComponentMapper;
import com.lee.hof.sys.service.UserComponentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
public class UserComponentServiceImpl extends ServiceImpl<UserComponentMapper, UserComponent> implements UserComponentService {

    @Resource
    UserComponentMapper userComponentMapper;



    @Override
    public String addComponent(MultipartFile file, String componentId) throws IOException {
        InputStream input = file.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = -1;
        while((len = input.read(b)) != -1) {
            bos.write(b, 0, len);
        }
        UserComponent userComponent = new UserComponent();
        userComponent.setId(UUID.randomUUID().toString());
        userComponent.setUserId(2);
        userComponent.setComponentId(componentId);
        userComponent.setPhoto(bos.toByteArray());
        userComponentMapper.insert(userComponent);
        return userComponent.getId();
    }
}
