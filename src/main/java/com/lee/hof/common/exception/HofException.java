package com.lee.hof.common.exception;


import com.lee.hof.common.constant.HofStatus;

public class HofException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -4137688758944857209L;

    /**
     * http状态码
     */
    public Integer code;

    public String msg;


    public HofException(String msg){
        super(msg);
        this.code = HofStatus.CONTINUE.getCode();
    }

}
