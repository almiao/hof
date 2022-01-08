package com.lee.hof.sys.bean.model;

import com.lee.hof.sys.bean.dto.User;

/**
 * @author tangle
 * @description
 * @date 2021/9/7
 */

public class BaseInput {

    private String userId;

    private User user;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getEndInfo() {
        return endInfo;
    }

    public void setEndInfo(String endInfo) {
        this.endInfo = endInfo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String ip;

    private String endInfo;

    private String location;

}
