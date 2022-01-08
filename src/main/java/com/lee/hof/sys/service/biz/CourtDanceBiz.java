package com.lee.hof.sys.service.biz;

import com.lee.hof.sys.bean.dto.CourtDanceSpotAddDto;
import com.lee.hof.sys.bean.model.CourtDanceSpot;
import com.lee.hof.sys.bean.vo.CourtDanceSpotSimpleVO;
import com.lee.hof.sys.bean.vo.CourtDanceSpotVO;
import com.lee.hof.sys.service.CourtDanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourtDanceBiz {


    @Autowired
    CourtDanceService courtDanceService;


    public CourtDanceSpotVO addCourtDanceSpot(CourtDanceSpotAddDto danceSpotAddDto){

        CourtDanceSpot spot = courtDanceService.addDanceSpot(danceSpotAddDto);

        CourtDanceSpotVO courtDanceSpotVO = new CourtDanceSpotVO();

        BeanUtils.copyProperties(spot,courtDanceSpotVO);

        return courtDanceSpotVO;
    }

    public CourtDanceSpotVO updateCourtDanceSpot(CourtDanceSpotAddDto danceSpotAddDto){

        CourtDanceSpot spot = courtDanceService.updateDanceSpot(danceSpotAddDto);

        CourtDanceSpotVO courtDanceSpotVO = new CourtDanceSpotVO();

        BeanUtils.copyProperties(spot,courtDanceSpotVO);

        return courtDanceSpotVO;
    }


}
