package com.lee.hof;

import com.lee.hof.sys.bean.model.BaseInput;
import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.mapper.UserMapper;
import org.apache.catalina.mbeans.UserMBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Component
@org.aspectj.lang.annotation.Aspect
public class ControllerAOP {


    @Resource
    UserMapper userMapper;

    @Pointcut("@annotation(com.lee.hof.sys.controller.*)")
    public void logPointCut(){
    }

    @Around("logPointCut()")
    public void aroundAdvice(ProceedingJoinPoint jointPoint){
        try {
            jointPoint.proceed();
            //获取方法参数值
            Object[] args = jointPoint.getArgs();
            //根据方法请求参数值，获取方法的url
            BaseInput baseInput = null;
            if(args != null && args.length > 0){
                for (Object arg : args) {
                    if(arg instanceof BaseInput){
                        Long userId = baseInput.getUserId();
                        User user = userMapper.selectById(userId);
                        baseInput.setUser(user);
                        break;
                    }
                }
            }
            Signature signature = jointPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            System.out.println("进入方法：");
            System.out.println(method.getName());
            System.out.println("参数");
            assert baseInput != null;
            System.out.println(baseInput.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}

