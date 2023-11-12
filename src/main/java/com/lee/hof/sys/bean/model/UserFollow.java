package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@TableName("user_follow")
@Entity
@Table(name="user_follow")
public class UserFollow extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long toEntityId;

    /**
     * 关注类型
     */
    private int followType;


}
