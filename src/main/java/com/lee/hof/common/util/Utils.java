package com.lee.hof.common.util;

import java.util.Random;

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
}
