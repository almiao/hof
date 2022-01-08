package com.lee.hof.sys.bean.model;

import lombok.Data;

@Data
public class CourtDanceGroup {

    private int imageId;

    public CourtDanceGroup(String danceGroupName) {
        this.danceGroupName = danceGroupName;
    }

    //舞团名称 百望山舞团
    private String danceGroupName;

    //成员数量描述 30位成员
    private String memberDesc;

    //今日打卡描述 今日已有3位伙伴打卡加入
    private String todayEnrollDesc;

    //舞种描述 舞种类型
    private String danceType;

    //与所在舞点
    private String danceSpotName;

    //活跃度排名
    private String rankDesc;

    //得分
    private String score;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDanceGroupName() {
        return danceGroupName;
    }

    public void setDanceGroupName(String danceGroupName) {
        this.danceGroupName = danceGroupName;
    }

    public String getMemberDesc() {
        return memberDesc;
    }

    public void setMemberDesc(String memberDesc) {
        this.memberDesc = memberDesc;
    }

    public String getTodayEnrollDesc() {
        return todayEnrollDesc;
    }

    public void setTodayEnrollDesc(String todayEnrollDesc) {
        this.todayEnrollDesc = todayEnrollDesc;
    }

    public String getDanceType() {
        return danceType;
    }

    public void setDanceType(String danceType) {
        this.danceType = danceType;
    }

    public String getDanceSpotName() {
        return danceSpotName;
    }

    public void setDanceSpotName(String danceSpotName) {
        this.danceSpotName = danceSpotName;
    }

    public String getRankDesc() {
        return rankDesc;
    }

    public void setRankDesc(String rankDesc) {
        this.rankDesc = rankDesc;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
