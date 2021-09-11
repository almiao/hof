package com.lee.hof.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.lee.hof.sys.bean.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author tangle
 * @since 2021-09-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Ingredient extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer dishId;

    private String fileId;

    private String ingredientName;

    private String use;

    private String brand;

    private String source;

    private Integer isDel;


}
