package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.sys.bean.model.ShopDishDaily;
import com.lee.hof.sys.dao.ShopDishDailyMapper;
import com.lee.hof.sys.service.ShopDishDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangle
 * @since 2021-09-07
 */
@Service
public class ShopDishDailyServiceImpl extends ServiceImpl<ShopDishDailyMapper, ShopDishDaily> implements ShopDishDailyService {

    @Autowired
    ShopDishDailyMapper mapper;

}
