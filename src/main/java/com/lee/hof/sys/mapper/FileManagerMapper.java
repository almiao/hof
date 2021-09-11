package com.lee.hof.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lee.hof.sys.bean.model.FileManager;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tangle
 * @since 2021-09-11
 */
public interface FileManagerMapper extends BaseMapper<FileManager> {

    FileManager getByFileId(String fileId);

}
