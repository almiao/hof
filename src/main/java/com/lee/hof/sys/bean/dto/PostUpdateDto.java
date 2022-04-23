package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;

import java.util.List;


@Data
public class PostUpdateDto extends BaseInput {

    public String postId;

    public String name;

    public String contentHtml;

    public List<Long> topics;

    public String headText;

    public List<String> contentPics;



}
