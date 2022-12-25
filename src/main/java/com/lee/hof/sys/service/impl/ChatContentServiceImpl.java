package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.sys.bean.model.ChatContent;
import com.lee.hof.sys.mapper.ChatContentMapper;
import com.lee.hof.sys.service.ChatContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ChatContentServiceImpl extends ServiceImpl<ChatContentMapper, ChatContent> implements ChatContentService {

    @Resource
    ChatContentMapper chatContentMapper;



    @Override
    public ChatContent getLastByChatId(Long chatId) {

        QueryWrapper<ChatContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chat_id", chatId);
        queryWrapper.orderByDesc("id");
        return chatContentMapper.selectOne(queryWrapper);
    }

    @Override
    public List<ChatContent> getByChatId(Long chatId) {
        QueryWrapper<ChatContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chat_id", chatId);
        queryWrapper.orderByDesc("id");
        return chatContentMapper.selectList(queryWrapper);
    }
}
