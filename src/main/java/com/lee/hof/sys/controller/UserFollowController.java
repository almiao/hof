package com.lee.hof.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.hof.auth.UserContext;
import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.enums.UserFollowStatus;
import com.lee.hof.sys.bean.dto.UserFollowListDto;
import com.lee.hof.sys.bean.enums.UserFollowType;
import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.bean.model.UserFollow;
import com.lee.hof.sys.mapper.UserFollowMapper;
import com.lee.hof.sys.mapper.UserMapper;
import com.lee.hof.sys.service.UserService;
import com.lee.hof.sys.service.UserStatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/userfollow")
public class UserFollowController {


    @Resource
    UserFollowMapper userFollowMapper;


    @Resource
    UserService userService;

    @Resource
    UserStatisticService userStatisticService;

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
        userStatisticService.addFollowCnt();
        userStatisticService.addFollowedCnt(userFollow.getToEntityId());
        return BaseResponse.success(userFollow);
    }



    @PostMapping(value = "/delete")
    public BaseResponse<UserFollow> delete(@RequestBody UserFollow userFollowRequest){
        UserFollow userFollow = userFollowMapper.selectById(userFollowRequest.getId());
        if(userFollow.getStatus() ==UserFollowStatus.DELETED.getCode() ){
            return BaseResponse.success();
        }
        userFollow.setStatus(UserFollowStatus.DELETED.getCode());
        userFollowMapper.updateById(userFollow);
        userStatisticService.delFollowCnt();
        return BaseResponse.success(userFollow);
    }
    @PostMapping(value = "/hide")
    public BaseResponse<UserFollow> hide(@RequestBody UserFollow userFollowRequest){
        UserFollow userFollow = userFollowMapper.selectOne(new QueryWrapper<UserFollow>().eq("user_id", UserContext.getUserId()).eq("to_entity_id", userFollowRequest.getToEntityId()));

        if(userFollow!=null ){
            if(userFollow.getStatus() ==UserFollowStatus.HIDE.getCode()){
                return BaseResponse.success();
            }else{
                userFollow.setStatus(UserFollowStatus.HIDE.getCode());
                userFollowMapper.updateById(userFollow);
            }
        }else{
            userFollow = new UserFollow();
            userFollow.setUserId(UserContext.getUserId());
            userFollow.setToEntityId(userFollowRequest.getToEntityId());
            userFollow.setStatus(UserFollowStatus.HIDE.getCode());
            userFollowMapper.insert(userFollow);
        }
        return BaseResponse.success(userFollow);
    }

    /**
     * 查看我的关注列表
     * @return
     */
    @PostMapping(value = "/list")
    public BaseResponse<List<UserFollow>> list(@RequestBody UserFollowListDto userFollowListDto){

        if(userFollowListDto.isShowFollow()){
            List<UserFollow> userFollow = userFollowMapper.selectList(new QueryWrapper<UserFollow>()
                    .eq("user_id", userFollowListDto.getUserId())
                    .eq("status", UserFollowStatus.NORMAL.getCode()));

            userFollow.stream().forEach(userFollow1 -> {
                userFollow1.setUser(userService.getUserById(userFollow1.getToEntityId()));
            });
            return BaseResponse.success(userFollow);

        }else{
            List<UserFollow> userFollow = userFollowMapper.selectList(new QueryWrapper<UserFollow>()
                    .eq("to_entity_id", userFollowListDto.getUserId())
                    .eq("status", UserFollowStatus.NORMAL.getCode())
                    .eq("follow_type", UserFollowType.USER.getCode())
            );

            userFollow.stream().forEach(userFollow1 -> {
                userFollow1.setUser(userService.getUserById(userFollow1.getToEntityId()));
            });
            return BaseResponse.success(userFollow);
        }


    }

    /**
     * 查看是否对该用户右关注
     * @param userId
     * @return
     */
    @GetMapping(value = "/get")
    public BaseResponse<UserFollow> getUserFollow(@RequestParam Integer userId){
        UserFollow userFollow = userFollowMapper.selectOne(new QueryWrapper<UserFollow>().eq("user_id", UserContext.getUserId())
                .eq("to_entity_id", userId).eq("status",UserFollowStatus.NORMAL.getCode()));
        return BaseResponse.success(userFollow);
    }


    @Resource
    UserMapper userMapper;
    /**
     * 推荐关注的用户
     * @param
     * @return
     */
    @GetMapping(value = "/recommend")
    public BaseResponse<List<User>> recommend(){
        List<Integer> integerList = Arrays.asList(UserFollowStatus.NORMAL.getCode(), UserFollowStatus.HIDE.getCode());
        List<UserFollow> userFollow = userFollowMapper.selectList(new QueryWrapper<UserFollow>().select("to_entity_id")
                .eq("user_id", UserContext.getUserId())
                .in("status", integerList));
        Set<Long> ids = userFollow.stream().map(UserFollow::getToEntityId).collect(Collectors.toSet());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!ids.isEmpty()){
            queryWrapper.notIn("id",ids);
        }
        List<User> users = userMapper.selectList(queryWrapper);
        return BaseResponse.success(users);
    }

}
