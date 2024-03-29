package com.lee.hof.sys.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.afkbrb.avatar.Avatar;
import com.lee.hof.auth.JwtUtil;
import com.lee.hof.auth.UserContext;
import com.lee.hof.common.exception.HofException;
import com.lee.hof.common.upload.service.local.LocalStorageService;
import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.enums.ValidStatusEnum;
import com.lee.hof.sys.bean.enums.VerifyCodeEnum;
import com.lee.hof.sys.bean.model.*;
import com.lee.hof.sys.bean.model.component.VerifyMyCarContent;
import com.lee.hof.sys.bean.vo.UserDetailVO;
import com.lee.hof.sys.mapper.FileManagerMapper;
import com.lee.hof.sys.mapper.UserComponentMapper;
import com.lee.hof.sys.mapper.UserMapper;
import com.lee.hof.sys.service.UserStatisticService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    UserMapper userMapper;



    @Resource
    UserComponentMapper userComponentMapper;

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

    @Resource
    UserStatisticService userStatisticService;


    @PostMapping(value = "/detail")
    public BaseResponse<UserDetailVO> getDetail(@RequestParam Long id){
        User databaseUser = userMapper.selectById(id);
        if(databaseUser == null){
            return BaseResponse.badrequest();
        }
        UserDetailVO userDetailVO = new UserDetailVO();
        BeanUtils.copyProperties(databaseUser, userDetailVO);
        UserStatistic userStatistic = userStatisticService.getBaseMapper().selectOne(new QueryWrapper<UserStatistic>().eq("create_by", id));
        userDetailVO.setUserStatistic(userStatistic);
        return BaseResponse.success(userDetailVO);
    }

    @PostMapping(value = "/update")
    public BaseResponse<UserDetailVO> getDetail(@RequestBody User user){
        User databaseUser = userMapper.selectById(user.getId());
        if(databaseUser == null){
            return BaseResponse.badrequest();
        }
        databaseUser.setImgId(user.getImgId());
        databaseUser.setUsername(user.getUsername());
        databaseUser.setSignature(user.getSignature());
        databaseUser.setLocation(user.getLocation());
        databaseUser.setSex(user.getSex());
        userMapper.updateById(databaseUser);
        return getDetail(user.getId());
    }




    @PostMapping(value = "/listContact")
    public BaseResponse<List<User>> listContact(@RequestParam(required = false) String searchParam){
        List<User> databaseUser = userMapper.selectList(new QueryWrapper<>());
        return BaseResponse.success(databaseUser);
    }


    @PostMapping(value = "/getComponent")
    public BaseResponse<UserComponent> getUserComponent(@RequestParam(required = true) String verifyCode){
        UserComponent databaseUser = userComponentMapper.selectOne(new QueryWrapper<UserComponent>().eq("user_id", UserContext.getUserId()).eq("verify_code",verifyCode).eq("valid_status", ValidStatusEnum.VALID.getCode()));
        return BaseResponse.success(databaseUser);
    }

    @PostMapping(value = "/addComponent")
    public BaseResponse<UserComponent> addUserComponent(@RequestBody UserComponent request){
        UserComponent current = userComponentMapper.selectOne(new QueryWrapper<UserComponent>().eq("user_id", UserContext.getUserId()).eq("verify_code", request.getVerifyCode()).orderByDesc("id").last("limit 1"));
        UserComponent newComponent = new UserComponent();
        newComponent.setContent(request.getContent());
        newComponent.setUserId(UserContext.getUserId());
        newComponent.setVerifyCode(request.getVerifyCode());
        newComponent.setValidStatus(ValidStatusEnum.VALID.getCode());
        if(current != null){
            newComponent.setVersion(current.getVersion() +1);
            current.setValidStatus(ValidStatusEnum.UNVALID.getCode());
            userComponentMapper.updateById(current);
        }else{
            newComponent.setVersion(1);
        }

        if(request.getVerifyCode().equals(VerifyCodeEnum.D006.getVerifyCode())){
            VerifyMyCarContent myCarContent = JSONObject.parseObject(request.getContent(), VerifyMyCarContent.class);
            User user = userMapper.selectById(UserContext.getUserId());
            user.setLabel(myCarContent.getBrandName());
            userMapper.updateById(user);
        }

        userComponentMapper.insert(newComponent);
        return BaseResponse.success(newComponent);
    }


    @PostMapping(value = "/delUserComponent")
    public BaseResponse<UserComponent> delUserComponent(@RequestParam Long componentId){
        UserComponent current = userComponentMapper.selectOne(new QueryWrapper<UserComponent>().eq("user_id", UserContext.getUserId()).eq("id", componentId));
        if(current == null){
            throw new HofException("用户组件不存在");
        }
        current.setValidStatus(ValidStatusEnum.UNVALID.getCode());
        userComponentMapper.updateById(current);
        return BaseResponse.success(current);
    }


}
