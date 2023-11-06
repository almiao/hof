package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.common.util.Utils;
import com.lee.hof.sys.bean.model.DriverGroup;
import com.lee.hof.sys.bean.model.DriverGroupUser;
import com.lee.hof.sys.mapper.DriverGroupUserMapper;
import com.lee.hof.sys.mapper.GroupMapper;
import com.lee.hof.sys.service.DriverGroupService;
import com.lee.hof.sys.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangle
 * @since 2021-09-11
 */
@Service
public class DriverGroupServiceImpl extends ServiceImpl<GroupMapper, DriverGroup> implements DriverGroupService {

    @Resource
    GroupMapper groupMapper;


    @Resource
    DriverGroupUserMapper driverGroupUserMapper;



    @Override
    public List<DriverGroup> listGroup() {
        return groupMapper.selectList(new QueryWrapper<>());
    }


    @Override
    public DriverGroup addOrUpdate(DriverGroup input) {
        DriverGroup driverGroupResult = null;
        if(input.getId() != null){
            DriverGroup driverGroup = groupMapper.selectById(input.getId());
            BeanUtils.copyProperties(input, driverGroup, Utils.getNullPropertyNames(driverGroup));
            driverGroupResult = driverGroup;
        }else{
            groupMapper.insert(input);
            driverGroupResult = input;
        }
        return driverGroupResult;
    }

    @Resource
    UserService userService;

    @Override
    public List<DriverGroupUser> listDriverGroupUser(Long driverGroupId) {
        List<DriverGroupUser> driverGroupUsers =
                driverGroupUserMapper.selectList(new QueryWrapper<DriverGroupUser>().eq("driver_group_id", driverGroupId));
        driverGroupUsers.stream().forEach(driverGroupUser -> {
            driverGroupUser.setUser(userService.getUserById(driverGroupUser.getUserId()));
        });
        return driverGroupUsers;
    }


    @Override
    public DriverGroupUser addUser(Long driverGroupId) {
        DriverGroupUser driverGroupUser =
                driverGroupUserMapper.selectOne(new QueryWrapper<DriverGroupUser>().eq("driver_group_id", driverGroupId).eq("user_id", UserContext.getUserId()));

        if(driverGroupUser != null){
            return driverGroupUser;
        }

        driverGroupUser = new DriverGroupUser();
        driverGroupUser.setDriverGroupId(driverGroupId);
        driverGroupUser.setUserId(UserContext.getUserId());

        driverGroupUserMapper.insert(driverGroupUser);

        return driverGroupUser;
    }


}
