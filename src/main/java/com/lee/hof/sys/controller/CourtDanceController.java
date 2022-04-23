package com.lee.hof.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.hof.sys.bean.PageVO;
import com.lee.hof.sys.bean.dto.*;
import com.lee.hof.sys.bean.vo.CourtDanceGroupVO;
import com.lee.hof.sys.bean.vo.CourtDanceSpotVO;
import com.lee.hof.sys.service.CourtDanceSpotService;
import com.lee.hof.sys.service.biz.CourtDanceBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/courtDance")
public class CourtDanceController {


    @Autowired
    CourtDanceBiz courtDanceBiz;

    @PostMapping("/spot/list")
    public ResponseEntity<List<CourtDanceSpotVO>> getDanceSpots(CourtDanceSpotSearchDto dto){
        return ResponseEntity.ok(courtDanceBiz.getDanceSpots(dto));
    }

    @PostMapping("/spot/add")
    public ResponseEntity<CourtDanceSpotVO> addDanceSpot(@RequestBody CourtDanceSpotAddDto courtDanceSpotAddDto){
        return ResponseEntity.ok(courtDanceBiz.addCourtDanceSpot(courtDanceSpotAddDto));
    }

    @PostMapping("/spot/update")
    public ResponseEntity<CourtDanceSpotVO> updateDanceSpot(@RequestBody CourtDanceSpotUpdateDto updateDto){
        return ResponseEntity.ok(courtDanceBiz.updateCourtDanceSpot(updateDto));
    }

    @PostMapping("/group/list")
    public ResponseEntity<PageVO<CourtDanceGroupVO>> getDanceGroups(@RequestBody CourtDanceGroupSearchDto dto) {
        return ResponseEntity.ok(courtDanceBiz.getDanceGroups(dto));
    }

    @PostMapping("/group/add")
    public ResponseEntity<CourtDanceGroupVO> createDanceGroup(@RequestBody CourtDanceGroupAddDto dto) {
        return ResponseEntity.ok(courtDanceBiz.addCourtDanceGroup(dto));
    }


}
