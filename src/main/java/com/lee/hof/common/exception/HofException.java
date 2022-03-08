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
        this.msg = msg;
        this.code = HofStatus.CONTINUE.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
