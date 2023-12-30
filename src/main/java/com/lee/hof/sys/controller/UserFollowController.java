package com.lee.hof.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.hof.auth.UserContext;
import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.UserFollowStatus;
import com.lee.hof.sys.bean.model.UserFollow;
import com.lee.hof.sys.mapper.UserFollowMapper;
import com.lee.hof.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/userfollow")
public class UserFollowController {


    @Resource
    UserFollowMapper userFollowMapper;


    @Resource
    UserService userService;

    @PostMapping(value = "/add")
    public BaseResponse<UserFollow> add(@RequestBody UserFollow userFollowRequest ){
        UserFollow userFollow = userFollowMapper.selectOne(new QueryWrapper<UserFollow>().eq("user_id", UserContext.getUserId()).eq("to_entity_id", userFollowRequest.getToEntityId()));

        if(userFollow == null){
            userFollow = userFollowRequest;
            userFollow.setToEntityId(userFollowRequest.getToEntityId());
            userFollow.setUserId(UserContext.getUserId());
            userFollow.setStatus(UserFollowStatus.NORMAL.getCode());
            userFollowMapper.insert(userFollow);
        }else{
            if(userFollowRequest.getStatus() == userFollow.getStatus()){
                return BaseResponse.success(userFollow);
            }
            userFollow.setStatus(userFollowRequest.getStatus() == null? UserFollowStatus.NORMAL.getCode():userFollowRequest.getStatus());
            userFollowMapper.updateById(userFollow);
        }

        return BaseResponse.success(userFollow);
    }



    @PostMapping(value = "/delete")
    public BaseResponse<UserFollow> delete(@RequestBody UserFollow userFollowRequest){
        UserFollow userFollow = userFollowMapper.selectById(userFollowRequest.getId());
        userFollow.setStatus(UserFollowStatus.DELETED.getCode());
        userFollowMapper.updateById(userFollow);
        return BaseResponse.success(userFollow);
    }


    @PostMapping(value = "/list")
    public BaseResponse<List<UserFollow>> list(){
        List<UserFollow> userFollow = userFollowMapper.selectList(new QueryWrapper<UserFollow>().eq("user_id", UserContext.getUserId()));
        userFollow.stream().forEach(userFollow1 -> {
            userFollow1.setUser(userService.getUserById(userFollow1.getToEntityId()));
        });
        return BaseResponse.success(userFollow);
    }

    @GetMapping(value = "/get")
    public BaseResponse<UserFollow> getUserFollow(@RequestParam Integer userId){
        UserFollow userFollow = userFollowMapper.selectOne(new QueryWrapper<UserFollow>().eq("user_id", UserContext.getUserId())
                .eq("to_entity_id", userId));
        return BaseResponse.success(userFollow);
    }








}
