package com.lee.hof.sys.service.impl;

import com.lee.hof.sys.bean.dto.CourtDanceSpotAddDto;
import com.lee.hof.sys.bean.dto.CourtDanceSpotUpdateDto;
import com.lee.hof.sys.bean.model.CourtDanceGroup;
import com.lee.hof.sys.bean.model.CourtDanceSpot;
import com.lee.hof.sys.bean.vo.CourtDanceSpotSimpleVO;
import com.lee.hof.sys.mapper.CourtDanceSpotMapper;
import com.lee.hof.sys.service.CourtDanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

@Service
public class CourtDanceServiceImpl extends ServiceImpl<CourtDanceSpotMapper, CourtDanceSpot> implements CourtDanceService {

    @Resource
    CourtDanceSpotMapper mapper;


    @Override
    public List<CourtDanceSpotSimpleVO> getDanceSpots(String userId, String address) {

        CourtDanceSpotSimpleVO spot1 = new CourtDanceSpotSimpleVO("广泰小区舞点");
        spot1.setDistanceDesc("距您1.2km");
        spot1.setLogoImageId(0);
        spot1.setTeamDesc("该舞点共有3个舞团");
        spot1.setMemberDesc("123位成员");
        spot1.setPositionDesc("公园内");

        CourtDanceSpotSimpleVO spot2 = new CourtDanceSpotSimpleVO("万树园舞点");
        spot2.setDistanceDesc("距您3.1km");
        spot2.setLogoImageId(0);
        spot2.setTeamDesc("该舞点共有3个舞团");
        spot1.setMemberDesc("8位成员");
        spot2.setPositionDesc("小区内");

        CourtDanceSpotSimpleVO spot3 = new CourtDanceSpotSimpleVO("中关村舞点");
        spot3.setDistanceDesc("距您0.2km");
        spot3.setLogoImageId(0);
        spot3.setTeamDesc("该舞点共有3个舞团");
        spot1.setMemberDesc("9位成员");
        spot3.setPositionDesc("小区内");

        return Arrays.asList(spot1,spot2,spot3);
    }

    @Override
    public List<CourtDanceGroup> getDanceGroups(String userId, String address) {

        List<CourtDanceGroup> result = new ArrayList<>();

        return null;
    }


    public CourtDanceSpot addDanceSpot(CourtDanceSpotAddDto dto) {
        CourtDanceSpot courtDanceSpot = new CourtDanceSpot();
        courtDanceSpot.setId(System.currentTimeMillis() * 1000 + new Random().nextInt(1000));
        courtDanceSpot.setName(dto.getDanceSpotName());
        courtDanceSpot.setLogoImgId(dto.getLogoImgId());
        courtDanceSpot.setAttentionDesc(dto.getAttentionDesc());
        courtDanceSpot.setCreateByUserName(dto.getUser().getName());
        courtDanceSpot.setCreateTime(new Timestamp(System.currentTimeMillis()));
        courtDanceSpot.setDanceTypes(dto.getDanceTypes());
        courtDanceSpot.setDetailDesc(dto.getDetailDesc());
        mapper.insert(courtDanceSpot);

        return  courtDanceSpot;
    }

    @Override
    public CourtDanceSpot updateDanceSpot(CourtDanceSpotUpdateDto courtDanceSpotAddDto) {
        CourtDanceSpot old = mapper.selectById(courtDanceSpotAddDto.getId());
        BeanUtils.copyProperties(courtDanceSpotAddDto,old);
        return old;
    }



}
