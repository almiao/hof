package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.sys.bean.dto.TopicListDto;
import com.lee.hof.sys.bean.model.Topic;
import com.lee.hof.sys.mapper.TopicMapper;
import com.lee.hof.sys.service.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangle
 * @since 2021-09-12
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {


    @Resource
    TopicMapper topicMapper;

    public List<Topic> list(TopicListDto topicListDto){

        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        if (topicListDto.getSearchTxt() != null) {
            queryWrapper.like("name", topicListDto.getSearchTxt());
        }

        Page<Topic> topicPage =  topicMapper.selectPage(new Page<>(topicListDto.getPageNum(), topicListDto.getPageSize()),queryWrapper);

        return topicPage.getRecords();
    }

}
