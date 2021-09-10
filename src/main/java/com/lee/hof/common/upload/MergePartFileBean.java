package com.lee.hof.common.upload;

/**
 * @ClassName : MergePartFileBean
 * @Description :
 * @Author : zfs
 * @Date : 2020-11-17
 */

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 合并分块文件
 */
@Getter
@Setter
public class MergePartFileBean {

    @NotBlank(message = "文件路径不能为空！")
    private String key;

    /**
     * 整个文件的唯一标识
     */
    @NotBlank(message = "文件标识不能为空！")
    private String uploadId;

    /**
     * 每个块文件的唯一标识
     */
    @NotEmpty(message = "文件块标识不能为空！")
    private List<String> partUniqs;

}
