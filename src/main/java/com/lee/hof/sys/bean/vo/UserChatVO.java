package com.lee.hof.sys.bean.vo;

import com.lee.hof.sys.bean.model.ChatContent;
import com.lee.hof.sys.bean.model.User;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class UserChatVO {

    private Long id;

    private Long fromUserId;

    private Long toUserId;

    private Long fromUserReadId;

    private Long toUserReadId;

    private Timestamp createTime;

    private Timestamp updateTime;

    private User toUser;

    private User fromUser;

    private Long relatedGood;

    private List<ChatContent> chatContents;

}
