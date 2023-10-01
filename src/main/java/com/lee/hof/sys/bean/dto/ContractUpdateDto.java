package com.lee.hof.sys.bean.dto;

import com.lee.hof.sys.bean.model.Contract;
import lombok.Data;


@Data
public class ContractUpdateDto extends Contract {

    private boolean currentIsBuyer;


    /**
     * 0 更新信息 ， 1。填写完成后发布， 2。内容确认后签署
     */
    private int stage;




}
