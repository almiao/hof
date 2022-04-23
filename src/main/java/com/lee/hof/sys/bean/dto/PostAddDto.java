package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;

import java.util.List;


@Data
public class PostAddDto extends BaseInput {

    public String postName;

    public String postContentHtml;

    public List<Long> topics;

    public String postHead;

    public List<String> contentPics;

}
