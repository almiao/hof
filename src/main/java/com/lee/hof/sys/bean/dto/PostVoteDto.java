package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;


@Data
public class PostVoteDto extends BaseInput {

    private Long id;

    private Long postId;

    private String option;

    private String optionComment;

    private boolean addLike;

    private boolean addUnlike;


    private boolean delLike;

    private boolean delUnlike;





}
