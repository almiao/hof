package com.lee.hof.sys.bean.dto;

import lombok.Data;



@Data
public class LikeListDto {


    private Long createBy;

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }
}
