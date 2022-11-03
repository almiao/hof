package com.lee.hof.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lee.hof.auth.JwtUtil;
import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.bean.model.UserToken;
import com.lee.hof.sys.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    UserMapper userMapper;


    @PostMapping(value = "/login")
    public BaseResponse<UserToken> login(@RequestBody User user, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        String username = user.getUsername();
        String password = user.getPassword();
        User databaseUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", username).eq("password", password));
        if(databaseUser == null){
            return BaseResponse.badrequest();
        }

        // 省略 账号密码验证
        // 验证成功后发送token
        String token = JwtUtil.sign(username,password);
        if (token != null){
            UserToken userToken = new UserToken();
            userToken.setId(user.getId().toString());
            userToken.setToken(token);
            userToken.setUsername(databaseUser.getUsername());
            response.addHeader("set-token", token);
            BaseResponse.success(token);
        }
        return BaseResponse.badrequest();
    }

}
