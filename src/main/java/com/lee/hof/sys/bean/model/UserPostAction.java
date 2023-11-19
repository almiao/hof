package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@TableName("user_post_action")
@Table(name = "user_post_action")
public class UserPostAction extends BaseEntity {

    @Id
    private String id;

    private long userId;

    private long postId;

    private int isLike;

    private int isUnlike;

    /**
     * 举报内容
     */
    private String reportOption;

    /**
     * 评分
     */
    private String score;

    /**
     * 有一个
     */
    private String option;

    private String optionComment;



}
