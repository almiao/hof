package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.dto.TopicListDto;
import com.lee.hof.sys.bean.model.Topic;
import com.lee.hof.sys.service.TopicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController extends BaseController {

    @Resource
    TopicService topicService;

    @PostMapping("/list")
    public BaseResponse<List<Topic>> list(@RequestBody TopicListDto dto) {
        return BaseResponse.success(topicService.list(dto)) ;
    }

    @PostMapping("/get")
    public BaseResponse<Topic> getById(@RequestParam Long topicId) {
        return BaseResponse.success(topicService.getById(topicId)) ;
    }

}

