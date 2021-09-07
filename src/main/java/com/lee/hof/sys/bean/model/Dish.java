package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author tangle
 * @description
 * @date 2021/9/7
 */

@Data
@TableName("dish")
public class Dish implements Serializable {
    private static final long serialVersionUID = 1876655654053364580L;
    /**
     * 运费模板id
     */
    @TableId
    @ApiModelProperty(value = "运费模板id",required=true)
    private Long id;

    /**
     * 运费模板名称
     */

    @ApiModelProperty(value = "运费模板名称",required=true)
    private Long shopId;

    /**
     * 店铺id
     */

    @ApiModelProperty(value = "用户id",required=true)
    private Long userId;

    /**
     */
    @ApiModelProperty(value = "",required=true)
    private Integer step;


    /**
     */
    @ApiModelProperty(value = "是否包邮 0:不包邮 1:包邮",required=true)
    private String name;

    /**
     */
    @ApiModelProperty(value = "是否含有包邮条件",required=true)
    private String description;


    /**
     */
    @ApiModelProperty(value = "是否含有包邮条件",required=true)
    private String fileName;

    /**
     */
    @ApiModelProperty(value = "是否含有包邮条件",required=true)
    private Integer isMaterial;


    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间",required=true)
    private Timestamp createdTime;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间",required=true)
    private Timestamp updatedTime;

}


