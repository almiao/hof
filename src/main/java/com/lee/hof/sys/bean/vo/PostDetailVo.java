package com.lee.hof.sys.bean.vo;

import com.lee.hof.sys.bean.model.BaseOutput;
import lombok.Data;

import java.util.List;


@Data
public class PostDetailVo extends BaseOutput {

    public String name;

    public String contentHtml;

    public List<Long> topics;

    public String headText;

    public List<String> contentPics;


    /**
     * 作者名
     */
    private String author;
    /**
     * 作者名简写
     */
    private String authorNamePrefixed;
    /**
     * 作者头像url
     */
    private String authorIconUrl;
    
    /**
     * 得分
     */
    private int score;
    /**
     * 类型
     */
    private int postType;
    /**
     * 投票类型
     */
    private int voteType;
    /**
     * 评论数
     */
    private int nComments;

}
