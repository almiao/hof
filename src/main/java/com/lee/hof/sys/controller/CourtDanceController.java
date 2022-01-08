package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.dto.DanceSpotAddDto;
import com.lee.hof.sys.bean.model.CourtDanceSpot;
import com.lee.hof.sys.service.CourtDanceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/spot/add")
    public ResponseEntity<List<CourtDanceSpot>> addDanceSpot(@RequestBody DanceSpotAddDto danceSpotAddDto){
        return ResponseEntity.ok(courtDanceService.getDanceSpots(userId,address));
    }

    @GetMapping("/spot/list")
    public ResponseEntity<List<CourtDanceSpot>> getDanceSpotDetail(@Param("userId") String userId, @Param("address") String address){
        return ResponseEntity.ok(courtDanceService.getDanceSpots(userId,address));
    }

    @GetMapping("/spot/list")
    public ResponseEntity<List<CourtDanceSpot>> updateDanceSpotDetail(@Param("userId") String userId, @Param("address") String address){
        return ResponseEntity.ok(courtDanceService.getDanceSpots(userId,address));
    }

}
