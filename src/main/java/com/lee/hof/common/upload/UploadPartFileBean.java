package com.lee.hof.common.upload;

/**
 * @ClassName : UploadPartFileBean
 * @Description :
 * @Author : zfs
 * @Date : 2020-11-17
 */

import com.zhinantech.common.util.MockMultipartFile;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 上传分块文件
 */
@Getter
@Setter
public class UploadPartFileBean {

    @NotBlank(message = "文件路径不能为空！")
    private String key;

    /**
     * 整个文件的唯一标识
     */
    @NotBlank(message = "文件标识不能为空！")
    private String uploadId;

    /**
     * 已经切好片的文件  除了最后一个分片没有大小限制，其他的分片最小为100 KB。
     */
    @NotNull(message = "文件流不能为空！")
    private MockMultipartFile file;

    /**
     * 文件块的编号  取值范围是1~10000, 不在范围内会报错
     */
    @NotNull(message = "块编号不能为空！")
    private Integer partNumber;


}
