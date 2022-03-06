package com.lee.hof;

import com.alibaba.fastjson.JSONObject;
import com.lee.hof.sys.bean.model.BaseInput;
import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.mapper.UserMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@org.aspectj.lang.annotation.Aspect
public class ControllerAOP {

    private final static Logger logger = LoggerFactory.getLogger(ControllerAOP.class);

    @Resource
    UserMapper userMapper;


    @Pointcut("execution(public * com.lee.hof.sys.controller.*.*(..))")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint jointPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return;
        }

        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.debug("请求地址 : " + request.getRequestURL().toString());
        logger.debug("HTTP METHOD : " + request.getMethod());
        // 获取真实的ip地址
        logger.debug("CLASS_METHOD : " + jointPoint.getSignature().getDeclaringTypeName() + "."
                + jointPoint.getSignature().getName());
        logger.debug("参数 : " + Arrays.toString(jointPoint.getArgs()));

            //获取方法参数值
            Object[] args = jointPoint.getArgs();
            //根据方法请求参数值，获取方法的url
            BaseInput baseInput = null;
            if(args != null && args.length > 0){
                for (Object arg : args) {
                    if(arg instanceof BaseInput){
                        Long userId = ((BaseInput) arg).getUserId();
                        User user = userMapper.selectById(userId);
                        ((BaseInput) arg).setUser(user);
                        break;
                    }
                }
            }
    }


    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        // ob 为方法的返回值

        Object result;
        try {
            result= pjp.proceed();
            String declaringTypeName = pjp.getSignature().getDeclaringTypeName();
            String methodName = pjp.getSignature().getName();
            logger.info("方法：" + declaringTypeName + "."+ methodName +"，耗时：" + (System.currentTimeMillis() - startTime));
            logger.info(JSONObject.toJSONString(result));
        }catch (Throwable throwable){
            throwable.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("内部错误");
        }

        return result;
    }

}

