package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.model.ChatContent;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangle
 * @since 2021-09-12
 */
public interface ChatContentService extends IService<ChatContent> {

    ChatContent getLastByChatId(Long chatId);

    List<ChatContent> getByChatId(Long chatId);

}
