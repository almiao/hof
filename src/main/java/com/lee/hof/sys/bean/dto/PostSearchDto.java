package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;


@Data
public class PostSearchDto extends BaseInput {


    private String searchText;

    private int pageNum = 0;

    private int pageSize = 10;


}
