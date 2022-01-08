package com.lee.hof.sys.controller;


<<<<<<< HEAD
import com.lee.hof.sys.bean.dto.CourtDanceSpotUpdateDto;
import com.lee.hof.sys.bean.model.CourtDanceGroup;
=======
import com.lee.hof.sys.bean.dto.CourtDanceSpotAddDto;
>>>>>>> abd35d21bfef111a4729863e6817d86cef6d50ec
import com.lee.hof.sys.bean.model.CourtDanceSpot;
import com.lee.hof.sys.bean.vo.CourtDanceSpotSimpleVO;
import com.lee.hof.sys.bean.vo.CourtDanceSpotVO;
import com.lee.hof.sys.service.CourtDanceService;
import com.lee.hof.sys.service.biz.CourtDanceBiz;
import org.apache.ibatis.annotations.Param;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/court_dance")
public class CourtDanceController {

    @Resource
    CourtDanceService courtDanceService;

    @Autowired
    CourtDanceBiz courtDanceBiz;

    @GetMapping("/spot/list")
    public ResponseEntity<List<CourtDanceSpotSimpleVO>> getDanceSpots(@Param("userId") String userId, @Param("address") String address){
        return ResponseEntity.ok(courtDanceService.getDanceSpots(userId,address));
    }

    @GetMapping("/group/list")
    public ResponseEntity<List<CourtDanceGroup>> getDanceGroups(@Param("userId") String userId, @Param("address") String address) {
        return ResponseEntity.ok(courtDanceService.getDanceGroups(userId, address));
    }

    @PostMapping("/spot/add")
    public ResponseEntity<CourtDanceSpotVO> addDanceSpot(@RequestBody CourtDanceSpotAddDto courtDanceSpotAddDto){
        return ResponseEntity.ok(courtDanceBiz.addCourtDanceSpot(courtDanceSpotAddDto)));
    }

    @PostMapping("/spot/update")
    public ResponseEntity<CourtDanceSpotVO> getDanceSpotDetail(@RequestBody CourtDanceSpotUpdateDto updateDto){
        return ResponseEntity.ok(courtDanceService.updateDanceSpot(updateDto));
    }

    @GetMapping("/spot/update")
    public ResponseEntity<List<CourtDanceSpotSimpleVO>> updateDanceSpotDetail(@Param("userId") String userId, @Param("address") String address){
        return ResponseEntity.ok(courtDanceService.getDanceSpots(userId,address));
    }

}
