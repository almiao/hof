package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.dto.VerifyComponentAddOrUpdateDto;
import com.lee.hof.sys.bean.dto.VerifyComponentGetDto;
import com.lee.hof.sys.bean.dto.VerifyItemListDto;
import com.lee.hof.sys.bean.model.VerifyComponent;
import com.lee.hof.sys.bean.model.VerifyItem;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
   * @author
 * @since 2021-09-11
 */
public interface VerifyItemService extends IService<VerifyItem> {
    List<VerifyItem> listVerifyItem(VerifyItemListDto dto);

    String updateVerifyComponent(VerifyComponentAddOrUpdateDto dto);

    VerifyComponent getVerifyComponent(VerifyComponentGetDto dto);
}
