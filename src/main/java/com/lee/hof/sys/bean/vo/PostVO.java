package com.lee.hof.sys.bean.vo;

import com.lee.hof.sys.bean.model.Post;
import com.lee.hof.sys.bean.model.User;
import lombok.Data;


@Data
public class PostVO extends Post {

    private boolean like;

    private boolean notLike;

    private String likeId;

    private String notLikeId;


    private User author;
}
