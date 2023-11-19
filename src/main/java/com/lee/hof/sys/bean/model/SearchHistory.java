package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author tangle
 * @description
 * @date 2021/9/7
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_follow")
public class SearchHistory  extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1876655654053364580L;

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String search_text;

}


