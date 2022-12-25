package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

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
@TableName("chat_content")
@Entity
@Table(name="chat_content")
public class ChatContent extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long fromUserId;

    private Long toUserId;

    private Long chatId;

    private int msgType;

    private Timestamp createTime;

    private String content;



}
