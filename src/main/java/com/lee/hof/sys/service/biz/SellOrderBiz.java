package com.lee.hof.sys.service.biz;

import com.lee.hof.sys.bean.dto.SellOrderNewDto;
import com.lee.hof.sys.service.SellOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellOrderBiz {


    @Autowired
    SellOrderService sellOrderService;

    public Long newSellOrder(SellOrderNewDto dto){
        return null;
    }

}
