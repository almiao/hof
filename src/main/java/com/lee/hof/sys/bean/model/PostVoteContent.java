package com.lee.hof.sys.bean.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


public class PostVoteContent implements Serializable {

    private String totalBudget;

    private String sex;

    private List<String> prefers;

    private List<PostOption> postOptions;

    private String description;


    private LocalDateTime validTime;



    private Integer meOptionPosition;


    private String meOptionComment;

    public String getMeOptionComment() {
        return meOptionComment;
    }

    public void setMeOptionComment(String meOptionComment) {
        this.meOptionComment = meOptionComment;
    }

    public Integer getMeOptionPosition() {
        return meOptionPosition;
    }

    public void setMeOptionPosition(Integer meOptionPosition) {
        this.meOptionPosition = meOptionPosition;
    }


    public LocalDateTime getValidTime() {
        return validTime;
    }

    public void setValidTime(LocalDateTime validTime) {
        this.validTime = validTime;
    }

    public String getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(String totalBudget) {
        this.totalBudget = totalBudget;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<String> getPrefers() {
        return prefers;
    }

    public void setPrefers(List<String> prefers) {
        this.prefers = prefers;
    }

    public List<PostOption> getPostOptions() {
        return postOptions;
    }

    public void setPostOptions(List<PostOption> postOptions) {
        this.postOptions = postOptions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
