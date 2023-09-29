package com.lee.hof.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.common.util.Utils;
import com.lee.hof.sys.bean.model.Contract;
import com.lee.hof.sys.mapper.ContractMapper;
import com.lee.hof.sys.service.ContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements ContractService {

    @Override
    public Contract createOrUpdate(Contract contract) {
        if(contract.getId() != null){
            Contract dataBase = getById(contract.getId());
            BeanUtils.copyProperties(contract, dataBase, Utils.getNullPropertyNames(contract));
            updateById(dataBase);
            return dataBase;
        }else{
            Contract contractNew = new Contract();
            BeanUtils.copyProperties(contract, contractNew);
            this.save(contractNew);
            return contractNew;
        }
    }

    @Override
    public List<Contract> list() {
        return getBaseMapper().selectList(new QueryWrapper<Contract>().eq("create_by", UserContext.getUserId()));
    }
}
