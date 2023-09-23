package com.lee.hof.common.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Utils {


    public static Long generateId(){
       return  System.currentTimeMillis() * 10000 + new Random().nextInt(10000);
    }
    public static String formatTime(long target ){
        long cur = System.currentTimeMillis();
        long gap = cur - target;
        if(gap<60000){
            return gap/10000 + "秒前";
        }else if(gap < 3600000){
            return gap/60000 +"分钟前";
        }else if(gap < 86400000L){
            return gap/3600000 +"小时前";
        }else if(gap < 2592000000L){
            return gap/86400000L +"天前";
        }else if(gap < (2592000000L*12)){
            return gap/2592000000L +"月前";
        }else {
            return gap/(2592000000L*12) +"年前";
        }
    }

    /**
     * 获取需要忽略的属性
     *
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            // 此处判断可根据需求修改
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
