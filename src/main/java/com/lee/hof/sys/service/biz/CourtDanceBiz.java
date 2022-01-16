package com.lee.hof.sys.service.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.hof.sys.bean.dto.*;
import com.lee.hof.sys.bean.model.CourtDanceGroup;
import com.lee.hof.sys.bean.model.CourtDanceSpot;
import com.lee.hof.sys.bean.vo.CourtDanceGroupVO;
import com.lee.hof.sys.bean.vo.CourtDanceSpotVO;
import com.lee.hof.sys.service.CourtDanceGroupService;
import com.lee.hof.sys.service.CourtDanceSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourtDanceBiz {


    @Autowired
    CourtDanceSpotService courtDanceSpotService;


    @Autowired
    CourtDanceGroupService courtDanceGroupService;


    public List<CourtDanceSpotVO> getDanceSpots(CourtDanceSpotSearchDto dto){
        Double maxLongitude = null;
        Double minLongitude = null;
        Double maxLatitude = null;
        Double minLatitude = null;

        if(!Objects.isNull(dto.getRadius()) && !Objects.isNull(dto.getLongitude())){
            maxLongitude = dto.getLongitude() + dto.getRadius();
            minLatitude = dto.getLongitude() - dto.getRadius();
            maxLatitude = dto.getLatitude() + dto.getRadius();
            minLatitude = dto.getLatitude() - dto.getRadius();
        }
        Page<CourtDanceSpot> page = courtDanceSpotService.getDanceSpots(
                dto.getName(),
                dto.getDanceType(),
                maxLongitude,
                minLongitude,
                maxLatitude,
                minLatitude,
                dto.getPageNo(),
                dto.getPageSize()
        );

        return page.getRecords().stream().map(courtDanceSpot -> {
            CourtDanceSpotVO vo = new CourtDanceSpotVO();
            BeanUtils.copyProperties(courtDanceSpot,vo);
            return vo;
        }).collect(Collectors.toList());
    }


    public CourtDanceSpotVO addCourtDanceSpot(CourtDanceSpotAddDto danceSpotAddDto){

        CourtDanceSpot spot = courtDanceSpotService.addDanceSpot(danceSpotAddDto);

        CourtDanceSpotVO courtDanceSpotVO = new CourtDanceSpotVO();

        BeanUtils.copyProperties(spot,courtDanceSpotVO);

        return courtDanceSpotVO;
    }

    public CourtDanceSpotVO updateCourtDanceSpot(CourtDanceSpotUpdateDto danceSpotAddDto){

        CourtDanceSpot spot = courtDanceSpotService.updateDanceSpot(danceSpotAddDto);

        CourtDanceSpotVO courtDanceSpotVO = new CourtDanceSpotVO();

        BeanUtils.copyProperties(spot,courtDanceSpotVO);

        return courtDanceSpotVO;
    }


    public CourtDanceGroupVO addCourtDanceGroup(CourtDanceGroupAddDto danceGroupAddDto){

        CourtDanceGroup group = courtDanceGroupService.addDanceGroup(danceGroupAddDto);

        CourtDanceGroupVO courtDanceSpotVO = new CourtDanceGroupVO();

        BeanUtils.copyProperties(group,courtDanceSpotVO);

        return courtDanceSpotVO;
    }

    public CourtDanceGroupVO updateCourtDanceGroup(CourtDanceGroupUpdateDto danceGroupAddDto){

        CourtDanceGroup group = courtDanceGroupService.updateDanceGroup(danceGroupAddDto);

        CourtDanceGroupVO courtDanceSpotVO = new CourtDanceGroupVO();

        BeanUtils.copyProperties(group,courtDanceSpotVO);

        return courtDanceSpotVO;
    }


    public IPage<CourtDanceGroupVO> getDanceGroups(CourtDanceGroupSearchDto dto) {

        Page<CourtDanceGroup> groups =
                courtDanceGroupService.searchDanceGroups(dto.getDetailDesc(),
                        dto.getDanceType(),dto.getCourtDanceSpotId(),
                        dto.getPageNum(),dto.getPageSize());

        return groups.convert(courtDanceSpot -> {
            CourtDanceGroupVO vo = new CourtDanceGroupVO();
            BeanUtils.copyProperties(courtDanceSpot,vo);
            return vo;
        });
    }
}
