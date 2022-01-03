package com.lee.hof.sys.service.impl;

import com.lee.hof.sys.bean.model.CourtDanceSpot;
import com.lee.hof.sys.service.CourtDanceService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CourtDanceServiceImpl implements CourtDanceService {


    @Override
    public List<CourtDanceSpot> getDanceSpots(String userId, String address) {

        CourtDanceSpot spot1 = new CourtDanceSpot("广泰小区舞点");
        spot1.setDistanceDesc("距您1.2km");
        spot1.setImageId(0);
        spot1.setTeamDesc("该舞点共有3个舞团");
        spot1.setMemberDesc("123位成员");
        spot1.setPositionDesc("公园内");

        CourtDanceSpot spot2 = new CourtDanceSpot("万树园舞点");
        spot2.setDistanceDesc("距您3.1km");
        spot2.setImageId(0);
        spot2.setTeamDesc("该舞点共有3个舞团");
        spot1.setMemberDesc("8位成员");
        spot2.setPositionDesc("小区内");

        CourtDanceSpot spot3 = new CourtDanceSpot("中关村舞点");
        spot3.setDistanceDesc("距您0.2km");
        spot3.setImageId(0);
        spot3.setTeamDesc("该舞点共有3个舞团");
        spot1.setMemberDesc("9位成员");
        spot3.setPositionDesc("小区内");

        return Arrays.asList(spot1,spot2,spot3);
    }
}
