package com.lee.hof.sys.bean.vo;


import com.lee.hof.sys.bean.model.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CollectVO implements Serializable {
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


    public CollectVO(){
    }



}
