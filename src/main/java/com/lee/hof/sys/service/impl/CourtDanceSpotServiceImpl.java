package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.common.util.Utils;
import com.lee.hof.sys.bean.dto.CourtDanceSpotAddDto;
import com.lee.hof.sys.bean.dto.CourtDanceSpotUpdateDto;
import com.lee.hof.sys.bean.model.CourtDanceSpot;
import com.lee.hof.sys.mapper.CourtDanceSpotMapper;
import com.lee.hof.sys.service.CourtDanceSpotService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

@Service
public class CourtDanceSpotServiceImpl extends ServiceImpl<CourtDanceSpotMapper, CourtDanceSpot> implements CourtDanceSpotService {

    @Resource
    CourtDanceSpotMapper mapper;


    @Override
    public Page<CourtDanceSpot> getDanceSpots(
                                        String name,
                                        String danceType,
                                        Double maxLongitude,
                                        Double minLongitude,
                                        Double maxLatitude,
                                        Double minLatitude,
                                        int pageNo,
                                        int pageSize) {
        QueryWrapper<CourtDanceSpot> conditions = new QueryWrapper<CourtDanceSpot>()
                .like(StringUtils.isNoneBlank(name),"name", name)
                .like(StringUtils.isNotBlank(danceType),"danceTypes",danceType)
                .between(!Objects.isNull(maxLatitude),"longitude",minLongitude,maxLongitude)
                .between(!Objects.isNull(maxLatitude),"latitude",minLatitude,maxLatitude);

        return  mapper.selectPage(new Page<>(pageNo, pageSize), conditions);
    }


    public CourtDanceSpot addDanceSpot(CourtDanceSpotAddDto dto) {
        CourtDanceSpot courtDanceSpot = new CourtDanceSpot();
        courtDanceSpot.setId(Utils.generateId());
        courtDanceSpot.setName(dto.getName());
        courtDanceSpot.setLogoImgId(dto.getLogoImgId());
        courtDanceSpot.setAttentionDesc(dto.getAttentionDesc());
        courtDanceSpot.setCreateByUserName(dto.getUser().getUsername());
        courtDanceSpot.setCreateTime(new Timestamp(System.currentTimeMillis()));
        courtDanceSpot.setDanceTypes(dto.getDanceTypes());
        courtDanceSpot.setDetailDesc(dto.getDetailDesc());
        courtDanceSpot.setCreateBy(dto.getUser().getId());
        courtDanceSpot.setUpdateBy(dto.getUser().getId());
        mapper.insert(courtDanceSpot);

        return  courtDanceSpot;
    }

    @Override
    public CourtDanceSpot updateDanceSpot(CourtDanceSpotUpdateDto courtDanceSpotAddDto) {
        CourtDanceSpot old = mapper.selectById(courtDanceSpotAddDto.getId());
        BeanUtils.copyProperties(courtDanceSpotAddDto,old);
        old.setUpdateBy(courtDanceSpotAddDto.getUser().getId());
        mapper.insert(old);
        return old;
    }



}
