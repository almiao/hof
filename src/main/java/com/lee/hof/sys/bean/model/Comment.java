package com.lee.hof.sys.bean.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName("comment")
@Entity
@Table(name="comment")
public class Comment implements Serializable {

    @Id
    private String id;

    private String postId;

    private String toCommentId;

    private Long userId;

    private String commentInfo;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;


    private String createTimeDesc;

    /**
     * 回复
     */
    private int replyNum;

    /**
     * 点赞
     */
    private int remark;
}