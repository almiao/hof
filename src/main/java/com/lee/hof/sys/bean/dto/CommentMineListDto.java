package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;


@Data
public class CommentMineListDto extends BaseInput {
    private int pageNum = 0;

    private int pageSize = 100;

}
