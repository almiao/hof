package com.lee.hof.sys.dao;

import com.lee.hof.sys.bean.model.Dish;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishMapper {

    Dish getById(@Param("id") Long id);

    List<Dish> getListByShop(@Param("shop") Long shopId);

}
