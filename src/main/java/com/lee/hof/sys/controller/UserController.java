package com.lee.hof.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.afkbrb.avatar.Avatar;
import com.lee.hof.auth.JwtUtil;
import com.lee.hof.common.upload.service.local.LocalStorageService;
import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.model.FileManager;
import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.bean.model.UserToken;
import com.lee.hof.sys.mapper.FileManagerMapper;
import com.lee.hof.sys.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    UserMapper userMapper;

    @Resource
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Resource
    private LocalStorageService localStorageService;

    @Resource
    private FileManagerMapper fileManagerMapper;


    @PostMapping(value = "/login")
    public BaseResponse<UserToken> login(@RequestBody User user, HttpServletResponse response) throws IOException {
        User databaseUser;
        String token;
        if(StringUtils.isNoneBlank(user.getPhone())){
            databaseUser = userMapper.selectOne(new QueryWrapper<User>().eq("phone", user.getPhone()));
            if(databaseUser == null){
                databaseUser = new User();
                databaseUser.setPhone(user.getPhone());
                databaseUser.setUsername(user.getPhone());
                String fileId = UUID.randomUUID().toString();
                Avatar avatar = new Avatar();
                BufferedImage bufferedImage =  avatar.generateAndGetAvatar();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", os);
                String localFilename = localStorageService.getLocalStoragePath() + "/" + user.getPhone()+fileId+"_avatar.png";
                FileUtils.forceMkdir(new File(localFilename).getParentFile());
                org.apache.commons.io.IOUtils.copy(new ByteArrayInputStream(os.toByteArray()), new FileOutputStream(localFilename));
                FileManager fileManager = new FileManager();
                fileManager.setName(user.getPhone()+"_avatar.png");
                fileManager.setFullPath(user.getPhone()+fileId+"_avatar.png");
                fileManager.setUuid(fileId);
                fileManager.setFileId(fileManager.getUuid());
                fileManager.setProvider("local");
//        // 参数列表
                fileManagerMapper.insert(fileManager);
                databaseUser.setImgId(fileManager.getFileId());
                userMapper.insert(databaseUser);
            }
            token = JwtUtil.sign(user.getPhone(), user.getValidNum());
        }else{
            String username = user.getUsername();
            String password = user.getPassword();
            databaseUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", username).eq("password", password));
            if(databaseUser == null){
                return BaseResponse.badrequest();
            }
            token = JwtUtil.sign(databaseUser.getUsername(), databaseUser.getPassword());
        }

        if (token != null){
            UserToken userToken = new UserToken();
            userToken.setId(databaseUser.getId().toString());
            userToken.setToken(token);
            userToken.setUsername(databaseUser.getUsername());
            userToken.setImgId(databaseUser.getImgId());
            response.addHeader("set-token", token);

            log.info("请求地址 : " + userToken);
            redisTemplate.opsForValue().set(token, databaseUser, 30, TimeUnit.DAYS);
            return BaseResponse.success(userToken);
        }else {
            log.info("bad token" );
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
