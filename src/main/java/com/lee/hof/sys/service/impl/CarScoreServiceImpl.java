package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.sys.bean.model.CarScore;
import com.lee.hof.sys.mapper.CarScoreMapper;
import com.lee.hof.sys.service.CarScoreService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangle
 * @since 2021-09-11
 */
@Service
public class CarScoreServiceImpl extends ServiceImpl<CarScoreMapper, CarScore> implements CarScoreService {


    @Override
    public CarScore add(CarScore like) {
        this.baseMapper.insert(like);
        return like;
    }
}
