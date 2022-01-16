package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.dto.CourtDanceSpotAddDto;
import com.lee.hof.sys.bean.dto.CourtDanceSpotUpdateDto;
import com.lee.hof.sys.bean.model.CourtDanceSpot;

public interface CourtDanceSpotService extends IService<CourtDanceSpot> {


    Page<CourtDanceSpot> getDanceSpots(String name,
                                   String danceType,
                                   Double maxLongitude,
                                   Double minLongitude,
                                   Double maxLatitude,
                                   Double minLatitude,
                                   int pageNo,
                                   int pageSize);

    CourtDanceSpot addDanceSpot(CourtDanceSpotAddDto courtDanceSpotAddDto);

    CourtDanceSpot updateDanceSpot(CourtDanceSpotUpdateDto courtDanceSpotAddDto);

}
