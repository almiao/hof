package com.lee.hof.sys.bean.vo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lee.hof.common.util.Utils;
import com.lee.hof.sys.bean.model.Comment;
import com.lee.hof.sys.bean.model.Like;
import com.lee.hof.sys.bean.model.User;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class LikeVO implements Serializable {
    private Long id;

    private Long createBy;

    private Long updateBy;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private PostSimpleVo post;

    private CommentMineVO commentVo;


    private String targetEntityType;

    private Long targetId;

    private User user;


    public LikeVO(){
    }



}
