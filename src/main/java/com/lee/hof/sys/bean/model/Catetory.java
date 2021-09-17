package com.lee.hof.sys.bean.model;

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
 * @since 2021-09-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Catetory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer parentId;

    private String name;

    private String description;

    private Integer isDel;

    private String fileId;


}
