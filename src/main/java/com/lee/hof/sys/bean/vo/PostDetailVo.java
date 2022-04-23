package com.lee.hof.sys.bean.vo;

import com.lee.hof.sys.bean.model.BaseOutput;
import lombok.Data;

import java.util.List;


@Data
public class PostDetailVo extends BaseOutput {

    public String name;

    public String contentHtml;

    public List<Long> topics;

    public String headText;

    public List<String> contentPics;

}
