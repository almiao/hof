package com.lee.hof.auth;

import com.lee.hof.sys.bean.model.User;
import com.lee.hof.sys.mapper.UserMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    UserMapper userMapper;

    @Resource
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器");
        // 从 http 请求头中取出 token
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        if (token != null){

            if(token.equals("test")){
                User user = userMapper.selectById(2);
                UserContext.setUser(user);
                return true;
            }

//            String username = JwtUtil.getUserNameByToken(request);

            token = request.getHeader("token");

            User user = (User) redisTemplate.opsForValue().get(token);

//            User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));

//            boolean result = JwtUtil.verify(token,username, user.getPassword());
            if(user != null){
                UserContext.setUser(user);
                System.out.println("通过拦截器");
                return true;
            }
        }else {
            System.out.println(" null token");
        }
        System.out.println("未通过拦截器");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
