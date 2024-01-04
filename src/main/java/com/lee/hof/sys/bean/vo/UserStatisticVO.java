package com.lee.hof.sys.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserStatisticVO {

    private long id;

    private Long userId;

    private Long likeReadId;

    private Long collectReadId;

    private Long commentReadId;

    private int needReadLikeCnt;



    private int needReadCollectCnt;

    private int needReadCommentCnt;

    private long lastLikeId;

    private long lastCommentId;

    private long lastCollectCnt;
}
