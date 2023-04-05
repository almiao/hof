package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.dto.CompanionAddDto;
import com.lee.hof.sys.bean.dto.CompanionListDto;
import com.lee.hof.sys.bean.model.Companion;
import com.lee.hof.sys.bean.vo.CompanionVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangle
 * @since 2021-09-12
 */
public interface CompanionService extends IService<Companion> {


    Companion addCompanion(CompanionAddDto dto);



    List<CompanionVO> listCompanion(CompanionListDto dto);
}
