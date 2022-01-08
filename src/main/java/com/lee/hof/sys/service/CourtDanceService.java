package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.dto.CourtDanceSpotAddDto;
import com.lee.hof.sys.bean.dto.CourtDanceSpotUpdateDto;
import com.lee.hof.sys.bean.model.CourtDanceGroup;
import com.lee.hof.sys.bean.model.CourtDanceSpot;
import com.lee.hof.sys.bean.model.ShopDishDaily;
import com.lee.hof.sys.bean.vo.CourtDanceSpotSimpleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourtDanceService extends IService<CourtDanceSpot> {


    List<CourtDanceSpotSimpleVO> getDanceSpots(@Param("userId") String userId, @Param("address") String address);


    List<CourtDanceGroup> getDanceGroups(@Param("userId") String userId, @Param("address") String address);

    CourtDanceSpot addDanceSpot(CourtDanceSpotAddDto courtDanceSpotAddDto);

    CourtDanceSpot updateDanceSpot(CourtDanceSpotUpdateDto courtDanceSpotAddDto);

}
