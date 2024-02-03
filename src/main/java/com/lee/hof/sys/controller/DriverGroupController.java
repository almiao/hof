package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.model.DriverGroup;
import com.lee.hof.sys.bean.model.DriverGroupUser;
import com.lee.hof.sys.mapper.UserMapper;
import com.lee.hof.sys.service.DriverGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/driverGroup")
public class DriverGroupController {


    @Resource
    DriverGroupService driverGroupService;

    @PostMapping(value = "/addOrUpdate")
    public BaseResponse<DriverGroup> addOrUpdate(@RequestBody DriverGroup driverGroup){
        return BaseResponse.success(driverGroupService.addOrUpdate(driverGroup));
    }



    @PostMapping(value = "/listGroup")
    public BaseResponse<List<DriverGroup>> listGroup(){
        List<DriverGroup> result = driverGroupService.list();
        result.forEach(driverGroup -> {
            List<DriverGroupUser> driverGroupUsers = driverGroupService.listDriverGroupUser(driverGroup.getId());
            driverGroup.setDriverGroupUsers(driverGroupUsers);
        });
        return BaseResponse.success(result);
    }

    @PostMapping(value = "/listMyFollow")
    public BaseResponse<List<DriverGroup>> listMyFollowGroup(){
        return BaseResponse.success(driverGroupService.listMyFollowGroup());
    }


    @PostMapping(value = "/listUser")
    public BaseResponse<List<DriverGroupUser>> listUser(@RequestParam Long driverGroupId){
        return BaseResponse.success(driverGroupService.listDriverGroupUser(driverGroupId));
    }


    @PostMapping(value = "/addUser")
    public BaseResponse<DriverGroupUser> addUser(@RequestParam Long driverGroupId){
        return BaseResponse.success(driverGroupService.addUser(driverGroupId));
    }


    @PostMapping(value = "/delUser")
    public BaseResponse<DriverGroupUser> delUser(@RequestParam Long driverGroupId){
        return BaseResponse.success(driverGroupService.delUser(driverGroupId));
    }

}
