package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.model.DriverGroup;
import com.lee.hof.sys.bean.model.DriverGroupUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
   * @author
 * @since 2021-09-11
 */
public interface DriverGroupService extends IService<DriverGroup> {
    
    List<DriverGroup> listGroup();

    DriverGroup addOrUpdate(DriverGroup input);

    List<DriverGroupUser> listDriverGroupUser(Long driverGroupId);
    DriverGroupUser addUser(Long driverGroupId);



    List<DriverGroup> listMyFollowGroup();

    DriverGroupUser delUser(Long driverGroupId);
}
