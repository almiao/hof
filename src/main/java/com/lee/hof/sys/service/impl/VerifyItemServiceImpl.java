package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.sys.bean.dto.VerifyComponentAddOrUpdateDto;
import com.lee.hof.sys.bean.dto.VerifyComponentGetDto;
import com.lee.hof.sys.bean.dto.VerifyItemListDto;
import com.lee.hof.sys.bean.model.VerifyComponent;
import com.lee.hof.sys.bean.model.VerifyComponentResponse;
import com.lee.hof.sys.bean.model.VerifyItem;
import com.lee.hof.sys.mapper.VerifyComponentMapper;
import com.lee.hof.sys.mapper.VerifyItemMapper;
import com.lee.hof.sys.service.VerifyItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
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
public class VerifyItemServiceImpl extends ServiceImpl<VerifyItemMapper, VerifyItem> implements VerifyItemService {

    @Resource
    VerifyItemMapper verifyItemMapper;


    @Resource
    VerifyComponentMapper verifyComponentMapper;

    @Override
    public List<VerifyItem> listVerifyItem(VerifyItemListDto dto) {

        List<VerifyItem> verifyItems = verifyItemMapper.selectList(new QueryWrapper<>());

        for(VerifyItem verifyItem:verifyItems){

            VerifyComponent verifyComponent = verifyComponentMapper.selectOne(new QueryWrapper<VerifyComponent>().eq("user_id",
                    UserContext.getUser().getId()).eq("verify_code",verifyItem.getVerifyCode()).eq("order_id",dto.getOrderId() == null ?"default":dto.getOrderId()).last(" limit 1"));

            if(verifyComponent != null){
                verifyItem.setVerifyStatus(1);
            }
        }

        return verifyItems;
    }

    @Override
    public String updateVerifyComponent(VerifyComponentAddOrUpdateDto dto) {
        VerifyComponent verifyComponent;

        if(dto.getVerifyComponentId()!=null){
            verifyComponent = verifyComponentMapper.selectById(dto.getVerifyComponentId());
        }else{
            verifyComponent = new VerifyComponent();
            verifyComponent.setId(UUID.randomUUID().toString());
        }

        verifyComponent.setCreateTime(new Timestamp(System.currentTimeMillis()));

        verifyComponent.setVerifyContent(dto.getVerifyContent());
        verifyComponent.setUserId(UserContext.getUser().getId());
        verifyComponent.setVerifyCode(dto.getVerifyCode());

        if(dto.getVerifyComponentId() == null){
            verifyComponentMapper.insert(verifyComponent);
        }else{
            verifyComponentMapper.updateById(verifyComponent);
        }

        return verifyComponent.getId();
    }

    @Override
    public VerifyComponent getVerifyComponent(VerifyComponentGetDto dto) {
        VerifyComponentResponse response = new VerifyComponentResponse();


        VerifyComponent verifyComponent = verifyComponentMapper.selectOne(new QueryWrapper<VerifyComponent>().eq("user_id",
                UserContext.getUser().getId()).eq("verify_code", dto.getVerifyCode()).orderByDesc("id").last( " limit 1"));


        return verifyComponent;
    }


}
