package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author tangle
 * @description
 * @date 2021/9/7
 */

@Data
@TableName("post")
@Entity
@Table(name="post")
public class Post implements Serializable{
    private static final long serialVersionUID = 1876655654053364580L;

    private Long createBy;

    private Long updateBy;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private Integer status;

    @Id
    private String id;
    private String channelName;

    private String channelNamePrefixed;

    private String channelIconUrl;

    private Long authorId;

    /**
     * 标题
     */
    private String title;


    private String images;
    /**
     * 文本
     */
    private String contentText;
    /**
     * 地址
     */
    private String url;
    /**
     * 视频地址
     */
    private String videoUrl;
    /**
     * 视频下载地址
     */
    private String videoDownloadUrl;

    private int likeCnt;
    private int notLikeCnt;


    private long postTimeMillis;

    private String postTime;
    /**
     * 得分
     */
    private String score;
    /**
     * 类型
     */
    private String postType;
    /**
     * 投票类型
     */
    private int voteType;
    /**
     * 评论数
     */
    private int commentCnt;

    /**
     * 是否隐藏
     */
    private boolean hidden;
    /**
     *
     */
    private boolean stickied;
    /**
     * 是否已收藏
     */
    private boolean archived;
    private boolean locked;
    private boolean saved;
    /**
     * 是否已读
     */
    private boolean isRead;
    private boolean isHiddenInRecyclerView = false;
    /**
     * 用户设置为隐藏
     */
    private boolean isHiddenManuallyByUser = false;



}


