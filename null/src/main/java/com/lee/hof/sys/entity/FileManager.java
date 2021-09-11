package com.lee.hof.sys.entity;

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
public class FileManager extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer fileId;

    private String name;

    private String fullPath;

    private Integer size;

    private String extension;

    private String provider;

    private String type;

    private String uploadId;

    private String partETag;

    private Integer statusId;


}
