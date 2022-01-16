package com.lee.hof.sys.service.impl;

import com.lee.hof.sys.bean.model.Category;
import com.lee.hof.sys.mapper.CategoryMapper;
import com.lee.hof.sys.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangle
 * @since 2021-09-12
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
