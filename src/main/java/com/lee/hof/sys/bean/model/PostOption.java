package com.lee.hof.sys.bean.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostOption {

    private int position;

    private String text;

    private String imagePath;

    private String imageContent;

    private int isText;

    private int cnt;
}