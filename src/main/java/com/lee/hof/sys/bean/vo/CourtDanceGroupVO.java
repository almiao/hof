package com.lee.hof.sys.bean.vo;


import com.lee.hof.sys.bean.model.BaseOutput;
import lombok.Data;

@Data
public class CourtDanceGroupVO extends BaseOutput {
    private Long id;

    //舞团名称 百望山舞团
    private String name;

    private String logoImageId;

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

}
