package com.lee.hof.auth;

import com.lee.hof.sys.bean.model.User;
import io.netty.util.concurrent.FastThreadLocal;

public class UserContext {
    /**FastThreadLocal快，稳，没内存泄露问题*/
    private static FastThreadLocal<User> userHolder = new FastThreadLocal<>();
    /**
     *设置用户到 FastThreadLocal
     */
    public static void setUser(User loginUser) {
        userHolder.set(loginUser);
    }
    /**
     *从FastThreadLocal中获取用户
     */
    public static User getUser() {
        return userHolder.get();
    }

    public static Long getUserId() {
        if(userHolder.get() == null){
            return null;
        }
        return userHolder.get().getId();
    }
    /**
     *清除FastThreadLocal，反之内存泄露
     */
    public static void remove() {
        userHolder.remove();
    }
}
