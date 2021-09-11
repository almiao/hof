package com.lee.hof.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.lee.hof.sys.bean.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class Dish extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer shopId;

    private Integer userId;

    private Integer step;

    private String name;

    private String description;

    private String fileName;

    private Integer isMaterial;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;


}
