package com.lee.hof.common;

import com.alibaba.fastjson.JSONObject;
import com.lee.hof.common.exception.HofException;
import com.lee.hof.sys.bean.BaseResponse;
import com.lee.hof.sys.bean.model.BaseInput;
import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.mapper.UserMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        logger.info("请求地址 : " + request.getRequestURL().toString());
        logger.info("HTTP METHOD : " + request.getMethod());
        // 获取真实的ip地址
        logger.info("CLASS_METHOD : " + jointPoint.getSignature().getDeclaringTypeName() + "."
                + jointPoint.getSignature().getName());
        logger.info("参数 : " + Arrays.toString(jointPoint.getArgs()));

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
            String declaringTypeName = pjp.getSignature().getDeclaringTypeName();
            String methodName = pjp.getSignature().getName();
            logger.info("方法：" + declaringTypeName + "."+ methodName);
            result= pjp.proceed();
            logger.info("耗时：" + (System.currentTimeMillis() - startTime));
            logger.info(JSONObject.toJSONString(result));
        }catch (HofException throwable){
            logger.error("报错："+throwable.getMsg());
           return BaseResponse.error(throwable.getMessage());
        } catch (Throwable throwable){
            throwable.printStackTrace();
            logger.error("内部异常:", throwable);
            return BaseResponse.error("内部错误");
        }

        return result;
    }

}

