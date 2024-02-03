package com.lee.hof.sys.bean.vo;

import com.lee.hof.sys.bean.model.UserStatistic;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class UserDetailVO {


    private Long id;

    private String username;

    private String phone;

    private String idCard;

    private String password;

    private String email;

    private String imgId;

    private String role;

    private String token;

    private String validNum;

    private String location;

    private String signature;

    private String sex;

    private String label;


    private UserStatistic userStatistic;




}
