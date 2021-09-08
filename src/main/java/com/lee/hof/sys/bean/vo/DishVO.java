package com.lee.hof.sys.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class DishVO {

    public Integer id;

    public Integer categoryId;

    public List<IngredientVO> ingredients;

    public List<IngredientVO> spices;

    public String name;

    public String description;

    public List<StepVO> stepVOS;

    public List<String> tags;

    public String mainFileId;


}
