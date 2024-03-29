package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tangle
 * @description
 * @date 2021/9/7
 */

@Data
@TableName("picture")
public class Picture extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1876655654053364580L;
    /**
     * 运费模板id
     */
    @TableId
    @ApiModelProperty(value = "id",required=true)
    private Long id;


    private String uuid;


    private String name;
    /**
     * 运费模板名称
     */
    @ApiModelProperty(value = "html格式内容",required=true)
    private String contentHtml;

    /**
     * 店铺id
     */
    @ApiModelProperty(value = "",required=true)
    private String headText;


    private String contentPics;

    public String getContentPics() {
        return contentPics;
    }

    public void setContentPics(String contentPics) {
        this.contentPics = contentPics;
        this.contentPicList = contentPics == null ? new ArrayList<>() : Arrays.asList(StringUtils.split(contentPics, ","));
    }


    public List<String> getContentPicList() {
        return contentPicList;
    }

    public void setContentPicList(List<String> contentPicList) {
        this.contentPicList = contentPicList;
        this.contentPics = CollectionUtils.isEmpty(contentPicList) ? "": StringUtils.join(contentPicList,",");
    }

    private List<String> contentPicList;

}


