package com.lee.hof.sys.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.hof.auth.UserContext;
import com.lee.hof.common.constant.ContractStatusEnum;
import com.lee.hof.common.util.Utils;
import com.lee.hof.sys.bean.dto.ContractUpdateDto;
import com.lee.hof.sys.bean.model.Contract;
import com.lee.hof.sys.mapper.ContractMapper;
import com.lee.hof.sys.service.ContractService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public Contract createOrUpdate(ContractUpdateDto contract) {
        if(contract.getId() != null){
            Contract dataBase = getById(contract.getId());
            BeanUtils.copyProperties(contract, dataBase, Utils.getNullPropertyNames(contract));
            processStatus(contract, dataBase);
            updateById(dataBase);
            return dataBase;
        }else{
            Contract contractNew = new Contract();
            BeanUtils.copyProperties(contract, contractNew);
            processStatus(contract, contractNew);
            this.save(contractNew);
            return contractNew;
        }
    }

    private void processStatus(ContractUpdateDto dto, Contract contract){
        if(dto.getStage() == 1){
            if(dto.isCurrentIsBuyer()){
                contract.setBuyerUserId(UserContext.getUserId());
                if(contract.getStatus() == ContractStatusEnum.SellerFilledNeedBuyerFill.getCode()){
                    contract.setStatus(ContractStatusEnum.FilledNeedSign.getCode());
                }else{
                    contract.setStatus(ContractStatusEnum.BuyerFilledNeedSellerFILL.getCode());
                }
            }else{
                contract.setSellerUserId(UserContext.getUserId());
                if(contract.getStatus() == ContractStatusEnum.BuyerFilledNeedSellerFILL.getCode()){
                    contract.setStatus(ContractStatusEnum.FilledNeedSign.getCode());
                }else{
                    contract.setStatus(ContractStatusEnum.SellerFilledNeedBuyerFill.getCode());
                }
            }
        }else if(dto.getStage() ==2){
            if(dto.isCurrentIsBuyer()){
                Assert.isTrue(Objects.equals(UserContext.getUserId(), contract.getBuyerUserId()));
                if(contract.getStatus() == ContractStatusEnum.SellerSignedNeedBuyerSign.getCode()){
                    contract.setStatus(ContractStatusEnum.Signed.getCode());
                }else{
                    contract.setStatus(ContractStatusEnum.BuyerSignedNeedSellerSign.getCode());
                }
            }else{
                Assert.isTrue(Objects.equals(UserContext.getUserId(), contract.getSellerUserId()));
                if(contract.getStatus() == ContractStatusEnum.BuyerSignedNeedSellerSign.getCode()){
                    contract.setStatus(ContractStatusEnum.Signed.getCode());
                }else{
                    contract.setStatus(ContractStatusEnum.SellerSignedNeedBuyerSign.getCode());
                }
            }
        }
    }


    @Override
    public List<Contract> list() {
        return getBaseMapper().selectList(new QueryWrapper<Contract>().eq("create_by", UserContext.getUserId()));
    }
}
