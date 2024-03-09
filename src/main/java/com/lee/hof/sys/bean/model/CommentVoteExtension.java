package com.lee.hof.sys.bean.model;

import lombok.Data;

import java.util.List;

@Data
public class CommentVoteExtension {

    private String option;

    private String optionNum;

    private List<String> appendList;

}
