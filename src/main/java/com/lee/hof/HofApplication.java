package com.lee.hof;

/**
 * @author tangle
 * @description
 * @date 2021/9/3
 */

import com.lee.hof.sys.bean.websockert.WebSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@MapperScan("com.lee.hof.sys.mapper")
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class HofApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(HofApplication.class, args);
        WebSocketServer.setApplicationContext(applicationContext);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HofApplication.class);
    }


}

