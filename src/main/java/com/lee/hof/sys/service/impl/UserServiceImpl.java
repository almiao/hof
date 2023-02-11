package com.lee.hof.sys.service.impl;

import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.mapper.UserMapper;
import com.lee.hof.sys.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    UserMapper userMapper;

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    @Cacheable(cacheNames="getUserById", key = "#id")
    public User getUserById(Long id) {
        logger.info("MYSQL 命中");
        return userMapper.selectById(id == null ? 1L : id);
    }
}
