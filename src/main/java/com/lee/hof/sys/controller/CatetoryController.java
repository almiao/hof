package com.lee.hof.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.hof.sys.bean.model.Catetory;
import com.lee.hof.sys.service.CatetoryService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.lee.hof.sys.controller.BaseController;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class CatetoryController extends BaseController {

    @Autowired
    CatetoryService catetoryService;


    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody Catetory catetory) {
       return ResponseEntity.ok(catetoryService.save(catetory)) ;
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody Catetory catetory) {
        return ResponseEntity.ok(catetoryService.updateById(catetory)) ;
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> remove(@RequestBody Catetory catetory) {
        return ResponseEntity.ok(catetoryService.removeById(catetory.getId())) ;
    }

    @PostMapping("/list")
    public ResponseEntity<IPage<Catetory>> list(String name,Integer parentId,Integer pageNo, Integer pageSize) {

        Page<Catetory> page = new Page<>(pageNo,pageSize);


        QueryWrapper<Catetory> wrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty(name)){

            wrapper.like("name",name);

        }

        if(!StringUtils.isEmpty(parentId)){

            wrapper.eq("parent_id",parentId);
        }


        IPage<Catetory> result  = catetoryService.page(page,wrapper);

        return ResponseEntity.ok(result) ;
    }




}

