package com.lee.hof.sys.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tangle
 * @description 文件
 * @date 2021/9/3
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class FileManager {

        private static final long serialVersionUID = 1L;

        @TableId(value = "id", type = IdType.AUTO)
        private Integer id;

        private String uuid;

        private String fileId;

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
