package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.sys.bean.model.UserChat;
import com.lee.hof.sys.bean.vo.UserChatVO;
import com.lee.hof.sys.mapper.UserChatMapper;
import com.lee.hof.sys.service.ChatContentService;
import com.lee.hof.sys.service.UserChatService;
import com.lee.hof.sys.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserChatServiceImpl extends ServiceImpl<UserChatMapper, UserChat> implements UserChatService {

    @Resource
    UserChatMapper userChatMapper;


    @Resource
    ChatContentService chatContentService;

    @Resource
    UserService userService;

    @Override
    public UserChatVO newChat(Long toUserId, Long relateGood) {

        Long fromUserId = UserContext.getUserId();
        log.warn(fromUserId.toString());
        QueryWrapper<UserChat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("from_user_id", fromUserId);
        queryWrapper.eq("to_user_id", toUserId);
        UserChat chat = userChatMapper.selectOne(queryWrapper);

        if(chat != null){
            return convert(chat);
        }
        QueryWrapper<UserChat> reverse =   new QueryWrapper<>();
        queryWrapper.eq("from_user_id",toUserId);
        queryWrapper.eq("to_user_id", fromUserId);
        UserChat reverseChat = userChatMapper.selectOne(reverse);
        if(reverseChat!= null){
            return convert(reverseChat);
        }

        UserChat userChat = new UserChat();
        userChat.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userChat.setFromUserId(fromUserId);
        userChat.setToUserId(toUserId);
        userChat.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        userChat.setRelateGood(relateGood);
        userChatMapper.insert(userChat);
        return convert(userChat);
    }

    @Override
    public List<UserChatVO> listUserChat() {
        long userId = UserContext.getUserId();
        QueryWrapper<UserChat> queryWrapper =   new QueryWrapper<>();
        queryWrapper.eq("from_user_id",userId).or().eq("to_user_id",userId);
        queryWrapper.orderByDesc("update_time");
        List<UserChat> chats = userChatMapper.selectList(queryWrapper);
        return chats.stream().map(this::convert).collect(Collectors.toList());
    }

    private UserChatVO convert(UserChat chat){
        UserChatVO userChatVO = new UserChatVO();
        userChatVO.setId(chat.getId());
        userChatVO.setCreateTime(chat.getCreateTime());
        userChatVO.setUpdateTime(chat.getUpdateTime());
        userChatVO.setFromUserId(chat.getFromUserId());
        userChatVO.setToUserId(chat.getToUserId());
        userChatVO.setToUser(userService.getUserById(chat.getToUserId()));
        userChatVO.setFromUser(userService.getUserById(chat.getFromUserId()));
        userChatVO.setChatContents(chatContentService.getByChatId(chat.getId()));
        userChatVO.setRelatedGood(chat.getRelateGood());
        return userChatVO;
    }


}
