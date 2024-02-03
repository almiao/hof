package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;

import java.util.Date;


@Data
public class PostListDto extends BaseInput {

    public String postId;

    public String postName;

    public String searchText;

    public Date startDate;

    private Date endDate;

    private String channelName;


    private int pageNum = 0;

    private int pageSize = 10;

    private String sortBy;

    private Long createBy;


    private boolean listMyFollow;
}
