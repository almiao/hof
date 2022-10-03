package com.lee.hof.sys.bean;


/**
 * 网络返回基类 支持泛型
 * Created by Tamic on 2016-06-06.
 */
    public class GuaziResponse<T> {

        private int code;
        private long timestamp;

        private String message;
        private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public boolean isOk() {
            return code == 0;
        }

    }
