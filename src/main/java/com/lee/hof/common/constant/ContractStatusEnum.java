package com.lee.hof.common.constant;

public enum ContractStatusEnum {

    init(0, "合同内容填写中"),
    BuyerFilledNeedSellerFILL(1001, "需卖方补充"),
    SellerFilledNeedBuyerFill(1002, "需买方补充"),
    FilledNeedSign(1003, "需双方签署"),
    BuyerSignedNeedSellerSign(1004, "需卖方签署"),
    SellerSignedNeedBuyerSign(1005, "需买方签署"),
    Signed(1006, "已签署");

    private final int code;

    private final String msg;

    ContractStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ContractStatusEnum getByCode(int code){

        for(ContractStatusEnum statusEnum:ContractStatusEnum.values()){
            if(statusEnum.getCode() == code){
                return statusEnum;
            }
        }

        return null;
    }


}
