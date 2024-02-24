package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.sys.bean.dto.LikeListDto;
import com.lee.hof.sys.bean.enums.CommonStatusEnum;
import com.lee.hof.sys.bean.enums.EntityTypeEnum;
import com.lee.hof.sys.bean.model.*;
import com.lee.hof.sys.bean.vo.LikeVO;
import com.lee.hof.sys.mapper.CarScoreMapper;
import com.lee.hof.sys.mapper.LikeMapper;
import com.lee.hof.sys.mapper.PostMapper;
import com.lee.hof.sys.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
