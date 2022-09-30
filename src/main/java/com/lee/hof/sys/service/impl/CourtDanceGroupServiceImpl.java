package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.common.exception.HofException;
import com.lee.hof.common.util.Utils;
import com.lee.hof.sys.bean.dto.CourtDanceGroupAddDto;
import com.lee.hof.sys.bean.dto.CourtDanceGroupUpdateDto;
import com.lee.hof.sys.bean.model.CourtDanceGroup;
import com.lee.hof.sys.mapper.CourtDanceGroupMapper;
import com.lee.hof.sys.service.CourtDanceGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
public class CourtDanceGroupServiceImpl extends ServiceImpl<CourtDanceGroupMapper, CourtDanceGroup> implements CourtDanceGroupService {

    @Resource
    CourtDanceGroupMapper mapper;


    @Override
    public Page<CourtDanceGroup> searchDanceGroups(String name,
                                                   String danceType,
                                                   Long danceSpotId,
                                                   int pageNo,
                                                   int pageSize) {

        QueryWrapper<CourtDanceGroup> conditions = new QueryWrapper<CourtDanceGroup>()
                .like(StringUtils.isNoneBlank(name),"name", name)
                .like(StringUtils.isNotBlank(danceType),"danceTypes",danceType)
                .orderByDesc("id","create_time");

        return  mapper.selectPage(new Page<>(pageNo, pageSize), conditions);
    }

    public CourtDanceGroup addDanceGroup(CourtDanceGroupAddDto dto) {

        valid(dto);

        CourtDanceGroup CourtDanceGroup = new CourtDanceGroup();
        CourtDanceGroup.setId(Utils.generateId());
        CourtDanceGroup.setName(dto.getName());
        CourtDanceGroup.setLogoImgId(dto.getLogoImgId());
        CourtDanceGroup.setAttentionDesc(dto.getAttentionDesc() == null ? "":dto.getAttentionDesc().trim());
        CourtDanceGroup.setCreateByUserName(dto.getUser().getUsername());
        CourtDanceGroup.setCreateTime(new Timestamp(System.currentTimeMillis()));
        CourtDanceGroup.setDanceTypes(dto.getDanceTypes());
        CourtDanceGroup.setDetailDesc(dto.getDetailDesc() == null ? "":dto.getDetailDesc().trim());
        CourtDanceGroup.setCreateBy(dto.getUser().getId());
        CourtDanceGroup.setUpdateBy(dto.getUser().getId());
        mapper.insert(CourtDanceGroup);
        return  CourtDanceGroup;
    }

    @Override
    public CourtDanceGroup updateDanceGroup(CourtDanceGroupUpdateDto CourtDanceGroupAddDto) {
        CourtDanceGroup group = mapper.selectById(CourtDanceGroupAddDto.getId());
        BeanUtils.copyProperties(CourtDanceGroupAddDto,group);
        group.setUpdateBy(CourtDanceGroupAddDto.getUser().getId());
        mapper.insert(group);
        return group;
    }


    private void valid(CourtDanceGroupAddDto addDto){

        if(StringUtils.isEmpty(addDto.getName())){
            throw new HofException("名称不能为空");
        }


        if(StringUtils.isEmpty(addDto.getLogoImgId())){
            throw new HofException("图片不能为空");
        }

    }


}
