package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.common.exception.HofException;
import com.lee.hof.common.util.Utils;
import com.lee.hof.sys.bean.enums.CommonStatusEum;
import com.lee.hof.sys.bean.model.DriverGroup;
import com.lee.hof.sys.bean.model.DriverGroupUser;
import com.lee.hof.sys.mapper.DriverGroupUserMapper;
import com.lee.hof.sys.mapper.GroupMapper;
import com.lee.hof.sys.service.DriverGroupService;
import com.lee.hof.sys.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        List<DriverGroup> driverGroups = groupMapper.selectList(new QueryWrapper<>());

        List<DriverGroupUser> driverGroupUsers = driverGroupUserMapper.selectList(new QueryWrapper<DriverGroupUser>().eq("user_id", UserContext.getUserId()).eq("status",0));

        Set<Long> longSet = driverGroupUsers.stream().map(DriverGroupUser::getDriverGroupId).collect(Collectors.toSet());

        driverGroups.forEach(driverGroup -> driverGroup.setMyFollow(longSet.contains(driverGroup.getId())));

        return driverGroups;
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
            driverGroupUser.setStatus(CommonStatusEum.INIT.getCode());
            driverGroupUserMapper.updateById(driverGroupUser);
            return driverGroupUser;
        }
        driverGroupUser = new DriverGroupUser();
        driverGroupUser.setDriverGroupId(driverGroupId);
        driverGroupUser.setUserId(UserContext.getUserId());
        driverGroupUserMapper.insert(driverGroupUser);

        return driverGroupUser;
    }

    @Override
    public DriverGroupUser delUser(Long driverGroupId) {
        DriverGroupUser driverGroupUser =
                driverGroupUserMapper.selectOne(new QueryWrapper<DriverGroupUser>()
                        .eq("driver_group_id", driverGroupId)
                        .eq("user_id", UserContext.getUserId())
                        .eq("status", CommonStatusEum.INIT)
                        .last("limit 1"));
        if(driverGroupUser == null){
            throw new HofException("数据不存在");
        }
        driverGroupUser.setStatus(CommonStatusEum.DELETE.getCode());
        driverGroupUserMapper.updateById(driverGroupUser);
        return driverGroupUser;
    }


    @Override
    public List<DriverGroup> listMyFollowGroup() {

        List<DriverGroupUser>  driverGroupUsers = driverGroupUserMapper.selectList(new QueryWrapper<DriverGroupUser>()
                .eq("user_id", UserContext.getUserId())
                .eq("status", CommonStatusEum.INIT.getCode()));

        Set<Long> longs = driverGroupUsers.stream().map(DriverGroupUser::getDriverGroupId).collect(Collectors.toSet());

        if(longs.isEmpty()){
            return new ArrayList<>();
        }

        return groupMapper.selectBatchIds(longs);
    }


}
