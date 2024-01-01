package com.lee.hof.sys.bean.vo;

import com.lee.hof.sys.bean.model.BaseOutput;
import com.lee.hof.sys.bean.model.User;
import lombok.Data;

import java.util.List;


@Data
public class PostSimpleVo extends BaseOutput {

    private Long id;

    private String contentText;

    private User author;

    private String images;

    private String title;

}
