package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.dto.LikeListDto;
import com.lee.hof.sys.bean.model.CarScore;
import com.lee.hof.sys.bean.model.Like;
import com.lee.hof.sys.bean.model.UndoLike;
import com.lee.hof.sys.bean.vo.LikeVO;
import com.lee.hof.sys.service.CarScoreService;
import com.lee.hof.sys.service.LikeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/carScore")
public class CarScoreController {


    @Resource
    CarScoreService carScoreService;

    @PostMapping("/add")
    public BaseResponse<CarScore> addLike(@RequestBody @Valid CarScore carScore){
        return BaseResponse.success(carScoreService.add(carScore));
    }


}
