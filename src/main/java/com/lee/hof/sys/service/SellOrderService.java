package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.dto.SellOrderListDto;
import com.lee.hof.sys.bean.model.SellOrder;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangle
 * @since 2021-09-12
 */
public interface SellOrderService extends IService<SellOrder> {

    Long newSellOrder();

    SellOrder saveSellOrder(SellOrder sellOrder);


    SellOrder validate(String sellOrder);


    SellOrder getEditingSellOrder();

    SellOrder getSellOrderDetail(Long orderId);

    Page<SellOrder> listPublished(SellOrderListDto dto);




}
