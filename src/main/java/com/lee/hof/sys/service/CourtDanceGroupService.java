package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.dto.CourtDanceGroupAddDto;
import com.lee.hof.sys.bean.dto.CourtDanceGroupUpdateDto;
import com.lee.hof.sys.bean.model.CourtDanceGroup;

public interface CourtDanceGroupService extends IService<CourtDanceGroup> {

    Page<CourtDanceGroup> searchDanceGroups(String desc,
                                            String danceType,
                                            Long danceSpotId,
                                            int pageNo,
                                            int pageSize);

    CourtDanceGroup addDanceGroup(CourtDanceGroupAddDto CourtDanceGroupAddDto);

    CourtDanceGroup updateDanceGroup(CourtDanceGroupUpdateDto CourtDanceGroupAddDto);

}
