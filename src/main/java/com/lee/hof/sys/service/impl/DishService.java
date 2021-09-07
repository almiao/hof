package com.lee.hof.sys.service.impl;

import com.lee.hof.sys.bean.model.Dish;
import com.lee.hof.sys.dao.DishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tangle
 * @description
 * @date 2021/9/7
 */
@Service
public class DishService {

    @Autowired
    DishMapper dishMapper;

    public Dish getDishById(Long id){
        return dishMapper.getById(id);
    }


}
