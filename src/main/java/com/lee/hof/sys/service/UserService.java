package com.lee.hof.sys.service;

import com.lee.hof.sys.bean.model.User;

/**
 * <p>
 *  服务类
 * </p>
 *
   * @author
 * @since 2021-09-11
 */
public interface UserService  {

    User getUserById(Long id);
    
}
