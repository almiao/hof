package com.lee.hof.sys.bean.model;

import com.lee.hof.sys.bean.model.BaseEntity;
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
public class DishStep extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer dishId;

    private String name;

    private Integer ordinal;

    private String files;

    private String description;

    private Integer isDel;


}
