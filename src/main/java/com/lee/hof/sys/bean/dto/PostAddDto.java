package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tangle
 * @description
 * @date 2021/9/7
 */

@Data
public class PostAddDto extends BaseInput implements Serializable {
    private static final long serialVersionUID = 1876655654053364580L;

    private String channelName;
    private String channelNamePrefixed;
    private String channelIconUrl;
    /**
     * 作者名
     */
    private String author;
    /**
     * 作者名简写
     */
    private String authorNamePrefixed;
    /**
     * 作者头像url
     */
    private String authorIconUrl;
    /**
     * 作者资质
     */
    private String authorFlair;
    /**
     * 作者资质地址
     */
    private String authorFlairHTML;
    /**
     * 标题
     */
    private String title;
    /**
     * 文本
     */
    private String selfText;
    /**
     * 无图片文本
     */
    private String selfTextPlain;
    /**
     * 文本剪切
     */
    private String selfTextPlainTrimmed;
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
    /***
     * 动态图id
     */
    private String gfycatId;
    private String streamableShortCode;

    private boolean isImgur;
    private boolean isGfycat;
    private boolean isStreamable;
    /**
     * 永久链接
     */
    private String permalink;

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
    private String nComments;
    /**
     *
     */
    private int upvoteRatio;

    /**
     * 是否隐藏
     */
    private boolean hidden;
    private boolean spoiler;
    /**
     * 浏览等级
     */
    private boolean nsfw;
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
    private boolean isCrosspost;
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


