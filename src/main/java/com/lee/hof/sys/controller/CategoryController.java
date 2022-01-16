package com.lee.hof.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.hof.sys.bean.model.Category;
import com.lee.hof.sys.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tangle
 * @since 2021-09-12
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Autowired
    CategoryService categoryService;


    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody Category catetory) {
       return ResponseEntity.ok(categoryService.save(catetory)) ;
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody Category catetory) {
        return ResponseEntity.ok(categoryService.updateById(catetory)) ;
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> remove(@RequestBody Category catetory) {
        return ResponseEntity.ok(categoryService.removeById(catetory.getId())) ;
    }

    @PostMapping("/list")
    public ResponseEntity<IPage<Category>> list(String name,Integer parentId,Integer pageNo, Integer pageSize) {

        Page<Category> page = new Page<>(pageNo,pageSize);
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }

        if(!StringUtils.isEmpty(parentId)){

            wrapper.eq("parent_id",parentId);
        }


        IPage<Category> result  = categoryService.page(page,wrapper);

        return ResponseEntity.ok(result) ;
    }




}

