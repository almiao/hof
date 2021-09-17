package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.vo.IngredientVO;
import com.lee.hof.sys.bean.vo.StepVO;

import java.util.List;


public class DishAddDto {

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
