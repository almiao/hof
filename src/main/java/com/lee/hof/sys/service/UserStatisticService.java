package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.model.Like;
import com.lee.hof.sys.bean.model.UndoLike;
import com.lee.hof.sys.bean.model.UserStatistic;
import com.lee.hof.sys.bean.vo.UserStatisticVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
   * @author
 * @since 2021-09-11
 */
public interface UserStatisticService extends IService<UserStatistic> {


    UserStatistic add(UserStatistic statistic);

    UserStatisticVO getMine();


    void addPostCnt();

    void addCommentCnt();

    void addLikeCnt();

    void addCollectCnt();


}
