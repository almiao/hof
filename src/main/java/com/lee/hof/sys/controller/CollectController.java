package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.model.Collect;
import com.lee.hof.sys.bean.model.Like;
import com.lee.hof.sys.bean.model.UndoLike;
import com.lee.hof.sys.bean.vo.CollectVO;
import com.lee.hof.sys.bean.vo.LikeVO;
import com.lee.hof.sys.service.CollectService;
import com.lee.hof.sys.service.LikeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {


    @Resource
    CollectService collectService;

    @PostMapping("/add")
    public BaseResponse<Long> addLike(@RequestBody Collect like){
        return BaseResponse.success(collectService.add(like));
    }

    @PostMapping("/del")
    public BaseResponse<Long> delLike(@RequestBody UndoLike like){
        return BaseResponse.success(collectService.undoLike(like));
    }

    @PostMapping("/listCollectMe")
    public BaseResponse<List<CollectVO>> listLikeMe(){
        return BaseResponse.success(collectService.listLikeMe());
    }
    @PostMapping("/listMyCollect")
    public BaseResponse<List<CollectVO>> listMyLike(){
        return BaseResponse.success(collectService.listMyLike());
    }

}
