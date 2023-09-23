package com.lee.hof.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.hof.sys.bean.model.Contract;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangle
 * @since 2021-09-12
 */
public interface ContractService extends IService<Contract> {

    Contract createOrUpdate(Contract contract);


}
