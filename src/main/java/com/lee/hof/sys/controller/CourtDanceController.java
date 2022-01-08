package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.model.CourtDanceGroup;
import com.lee.hof.sys.bean.model.CourtDanceSpot;
import com.lee.hof.sys.service.CourtDanceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/court_dance")
public class CourtDanceController {


    @Resource
    CourtDanceService courtDanceService;

    @GetMapping("/spot/list")
    public ResponseEntity<List<CourtDanceSpot>> getDanceSpots(@Param("userId") String userId, @Param("address") String address){
        return ResponseEntity.ok(courtDanceService.getDanceSpots(userId,address));
    }


    @GetMapping("/group/list")
    public ResponseEntity<List<CourtDanceGroup>> getDanceGroups(@Param("userId") String userId, @Param("address") String address){
        return ResponseEntity.ok(courtDanceService.getDanceGroups(userId,address));
    }

}
