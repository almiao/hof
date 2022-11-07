package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.model.Channel;
import com.lee.hof.sys.bean.model.Group;
import com.lee.hof.sys.service.ChannelService;
import com.lee.hof.sys.service.GroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/carlife")
public class CarlifeController {

    @Resource
    ChannelService channelService;

    @Resource
    GroupService groupService;

    @GetMapping("/channel/list")
    public BaseResponse<List<Channel>> getChannels(){
        return BaseResponse.success(channelService.listChannel());
    }

    @GetMapping("/group/list")
    public BaseResponse<List<Group>> getGroups(){
        return BaseResponse.success(groupService.listGroup());
    }

}
