package com.lee.hof.sys.controller;


import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.model.ChatContent;
import com.lee.hof.sys.bean.vo.UserChatVO;
import com.lee.hof.sys.service.ChatContentService;
import com.lee.hof.sys.service.UserChatService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController extends BaseController {

    @Resource
    UserChatService userChatService;

    @Resource
    ChatContentService chatContentService;

    @PostMapping("/list")
    public BaseResponse<List<UserChatVO>> list() {
        return BaseResponse.success(userChatService.listUserChat()) ;
    }

    @PostMapping("/get")
    public BaseResponse<UserChatVO> newChat(@RequestParam( "toUserId") Long toUserId,@RequestParam(value = "relateGood", required = false) Long relateGood) {
        return BaseResponse.success(userChatService.newChat(toUserId,relateGood)) ;
    }

    @PostMapping("/content/list")
    public BaseResponse<List<ChatContent>> listChatContent(@RequestParam("chatId") Long chatId) {
        return BaseResponse.success(chatContentService.getByChatId(chatId)) ;
    }
}

