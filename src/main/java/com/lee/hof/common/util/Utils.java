package com.lee.hof.common.util;

import java.util.Random;

public class Utils {


    public static Long generateId(){
       return  System.currentTimeMillis() * 10000 + new Random().nextInt(10000);
    }

}
