package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.BaseInput;
import lombok.Data;


@Data
public class UserFollowListDto {

    private Long userId;

    /**
     * 他的关注， fasle表示被关注
     */
    private boolean showFollow;
}
