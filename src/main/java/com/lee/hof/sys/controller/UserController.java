package com.lee.hof.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.hof.auth.JwtUtil;
import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.bean.model.UserToken;
import com.lee.hof.sys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    UserMapper userMapper;

    @Resource
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @PostMapping(value = "/login")
    public BaseResponse<UserToken> login(@RequestBody User user, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        String username = user.getUsername();
        String password = user.getPassword();
        User databaseUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", username).eq("password", password));
        if(databaseUser == null){
            return BaseResponse.badrequest();
        }
        String token = JwtUtil.sign(username,password);
        if (token != null){
            UserToken userToken = new UserToken();
            userToken.setId(databaseUser.getId().toString());
            userToken.setToken(token);
            userToken.setUsername(databaseUser.getUsername());
            response.addHeader("set-token", token);
            redisTemplate.opsForValue().set(token, databaseUser, 30, TimeUnit.DAYS);
            return   BaseResponse.success(userToken);
        }
        return BaseResponse.badrequest();
    }

    @PostMapping(value = "/auth")
    public BaseResponse<Boolean> auth(HttpServletRequest request){
        String token = request.getHeader("token");
        User user = (User) redisTemplate.opsForValue().get(token);
        if(user != null){
            return BaseResponse.success(true);
        }
        return BaseResponse.success(false);
    }


    @PostMapping(value = "/detail")
    public BaseResponse<User> getDetail(@RequestParam Long id){
        User databaseUser = userMapper.selectById(id);
        if(databaseUser == null){
            return BaseResponse.badrequest();
        }
        return BaseResponse.success(databaseUser);
    }

}
