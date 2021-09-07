package com.lee.hof.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.lee.hof.sys.bean.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tangle
 * @since 2021-09-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ShopDishDaily extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户
     */
    private Long shopId;

    /**
     * 上传用户
     */
    private Long userId;

    /**
     * 菜品
     */
    private Long dishId;

    /**
     * 所属日期
     */
    private LocalDateTime date;

    /**
     * 1=主食材，2=调料，3=材料分解，4=制作过程，5=成品
     */
    private Integer stepType;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 购买日期
     */
    private LocalDateTime purchaseDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 使用量
     */
    private String usageAmount;


}
