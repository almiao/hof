package com.lee.hof.service.impl;

import com.lee.hof.bean.model.Dish;
import com.lee.hof.dao.DishMapper;
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
