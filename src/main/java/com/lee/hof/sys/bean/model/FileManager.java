package com.lee.hof.sys.bean.model;

import lombok.Data;

/**
 * @author tangle
 * @description 文件
 * @date 2021/9/3
 */

@Data
public class FileManager {

        private Integer id;

        private String fileId;

        private String name;

        private String fullPath;

        private long size;

        private String extension;

        private String provider;

        private String type;

        private String uploadId;

        private String partETag;

        private Integer statusId;

}
