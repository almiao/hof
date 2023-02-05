package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.dto.TopicListDto;
import com.lee.hof.sys.bean.model.Topic;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangle
 * @since 2021-09-12
 */
public interface TopicService extends IService<Topic> {

    public List<Topic> list(TopicListDto topicListDto);

}
