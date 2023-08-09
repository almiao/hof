package com.lee.hof.sys.bean.vo;

import com.lee.hof.sys.bean.model.Companion;
import com.lee.hof.sys.bean.model.User;
import lombok.Data;

import java.util.List;

/**
 * @author tangle
 * @description
 * @date 2021/9/7
 */
@Data
public class CompanionVO extends Companion {
    private static final long serialVersionUID = 18766556543364580L;

    private User user;

    private List<User> companionUserList;
}


