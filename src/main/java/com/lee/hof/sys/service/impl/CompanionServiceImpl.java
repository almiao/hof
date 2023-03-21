package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.sys.bean.dto.CompanionAddDto;
import com.lee.hof.sys.bean.model.Companion;
import com.lee.hof.sys.mapper.CompanionMapper;
import com.lee.hof.sys.service.CompanionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@Service
public class CompanionServiceImpl extends ServiceImpl<CompanionMapper, Companion> implements CompanionService {

    @Resource
    CompanionMapper companionMapper;


    @Override
    public Companion addCompanion(CompanionAddDto dto) {

        Companion companion = new Companion();
        BeanUtils.copyProperties(dto, companion);

        companion.setCreateBy(UserContext.getUserId());
        companion.setCreateTime(LocalDateTime.now());

        companionMapper.insert(companion);

        return companion;
    }
}
