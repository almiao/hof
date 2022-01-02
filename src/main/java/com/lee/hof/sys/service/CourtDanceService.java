package com.lee.hof.sys.service;

import com.lee.hof.sys.bean.model.CourtDanceSpot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourtDanceService {


    List<CourtDanceSpot> getDanceSpots(@Param("userId") String userId, @Param("address") String address);
}
