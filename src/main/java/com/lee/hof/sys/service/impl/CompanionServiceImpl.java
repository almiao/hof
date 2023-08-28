package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.sys.bean.dto.CompanionAddDto;
import com.lee.hof.sys.bean.dto.CompanionJoinDto;
import com.lee.hof.sys.bean.dto.CompanionListDto;
import com.lee.hof.sys.bean.model.Companion;
import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.bean.vo.CompanionVO;
import com.lee.hof.sys.mapper.CompanionMapper;
import com.lee.hof.sys.service.CompanionService;
import com.lee.hof.sys.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


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

        int inserted =  companionMapper.insert(companion);

        System.out.println("创建成功：%d" + inserted);

        return companion;
    }

    @Override
    public CompanionVO joinCompanion(CompanionJoinDto dto) {
        Companion companion = companionMapper.selectById(dto.getCompanionId());
        if(StringUtils.isNoneBlank(companion.getCompanionUsers())){
            String[] userId = StringUtils.split(companion.getCompanionUsers());
            Set<String> re = new HashSet<>();
            Collections.addAll(re, userId);
            re.add(dto.getUserId().toString());
            companion.setCompanionUsers(StringUtils.join(re,","));
        }else{
            companion.setCompanionUsers(dto.getUserId().toString());
        }
        companionMapper.updateById(companion);
        return convert(companion);
    }

    @Resource
    private UserService userService;

    @Override
    public List<CompanionVO> listCompanion(CompanionListDto dto) {
         List<Companion> companions = companionMapper.selectList(new QueryWrapper<>());
         List<CompanionVO> companionVOS = companions.stream().map(this::convert).collect(Collectors.toList());
         return companionVOS;
    }


    private CompanionVO convert(Companion companion){
        CompanionVO companionVO = new CompanionVO();
        BeanUtils.copyProperties(companion, companionVO);
        companionVO.setUser(userService.getUserById(companion.getCreateBy()));
        List<User> users = new ArrayList<>();
        if(StringUtils.isNoneBlank(companion.getCompanionUsers())){
            String[] userId = StringUtils.split(companion.getCompanionUsers(),",");
            for(String id:userId){
                users.add(userService.getUserById(Long.parseLong(id)));
            }
        }
        companionVO.setCompanionUserList(users);
        return companionVO;

    }


}
