package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.model.UndoLike;
import com.lee.hof.sys.bean.model.UserStatistic;
import com.lee.hof.sys.bean.vo.UserStatisticVO;
import com.lee.hof.sys.service.UserStatisticService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/statistic")
public class UserStatisticController {


    @Resource
    UserStatisticService userStatisticService;

    @PostMapping("/addOrUpdate")
    public BaseResponse<UserStatistic> add(@RequestBody UserStatistic statistic){
        return BaseResponse.success(userStatisticService.add(statistic));
    }

    @PostMapping("/getMine")
    public BaseResponse<UserStatisticVO> getMine(){
        return BaseResponse.success(userStatisticService.getMine());
    }


}
