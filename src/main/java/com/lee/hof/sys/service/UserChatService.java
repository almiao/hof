package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.model.UserChat;
import com.lee.hof.sys.bean.vo.UserChatVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangle
 * @since 2021-09-12
 */
public interface UserChatService extends IService<UserChat> {

    UserChatVO newChat(Long toUserId,Long relateGood);

    boolean updateReadId(Long contentId, Long chatId);

    List<UserChatVO> listUserChat();

}
