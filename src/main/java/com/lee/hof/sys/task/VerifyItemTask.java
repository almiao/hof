package com.lee.hof.sys.task;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.hof.sys.bean.enums.ValidStatusEnum;
import com.lee.hof.sys.bean.enums.VerifyStatusEum;
import com.lee.hof.sys.bean.model.UserComponent;
import com.lee.hof.sys.bean.model.VerifyComponent;
import com.lee.hof.sys.mapper.UserComponentMapper;
import com.lee.hof.sys.mapper.VerifyComponentMapper;
import com.lee.hof.sys.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Component
public class VerifyItemTask {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    VerifyComponentMapper componentMapper;

    @Resource
    UserComponentMapper userComponentMapper;

    @Scheduled(fixedDelay = 1000*60)
    public void verify(){
        logger.info("start task");
        List<VerifyComponent> components = componentMapper.selectList(new QueryWrapper<VerifyComponent>().eq("verify_status", VerifyStatusEum.NEED_VERIFY.getCode()));
        int rand = new Random().nextInt(100);
        for(VerifyComponent verifyComponent:components){
            if(rand > 50){
                verifyComponent.setVerifyStatus(VerifyStatusEum.Success.getCode());
                UserComponent current = userComponentMapper.selectOne(new QueryWrapper<UserComponent>().eq("user_id", verifyComponent.getUserId()).eq("verify_code",verifyComponent.getVerifyCode()).orderByAsc("valid_status").last(" limit 1"));
                UserComponent newComponent = new UserComponent();
                newComponent.setContent(verifyComponent.getVerifyContent());
                newComponent.setUserId(verifyComponent.getUserId());
                newComponent.setVerifyCode(verifyComponent.getVerifyCode());
                newComponent.setValidStatus(ValidStatusEnum.VALID.getCode());
                if(current != null){
                    newComponent.setVersion(current.getVersion() +1);
                    current.setValidStatus(ValidStatusEnum.UNVALID.getCode());
                    userComponentMapper.updateById(current);
                }else{
                    newComponent.setVersion(1);
                }
                userComponentMapper.insert(newComponent);
                componentMapper.updateById(verifyComponent);
            }else{
                verifyComponent.setVerifyStatus(VerifyStatusEum.FAIL.getCode());
            }
        }
        logger.info("end task");
    }

}
