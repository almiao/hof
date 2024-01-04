package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.model.Like;
import com.lee.hof.sys.bean.model.UndoLike;
import com.lee.hof.sys.bean.vo.LikeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
   * @author
 * @since 2021-09-11
 */
public interface LikeService extends IService<Like> {


    Long add(Like like);

    String cnt();

    Long undoLike(UndoLike id);

    List<LikeVO> listLikeMe();

    List<LikeVO> listMyLike();
}
