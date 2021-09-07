package com.lee.hof.dao;

import com.lee.hof.bean.model.Dish;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Repository
public interface DishMapper extends BaseMapper<Dish> {

    Dish getById(@Param("id") Long id);

    List<Dish> getListByShop(@Param("shop") Long shopId);

}
