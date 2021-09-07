package com.lee.hof;

/**
 * @author tangle
 * @description
 * @date 2021/9/3
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@MapperScan("com.lee.hof.sys.dao")
@SpringBootApplication
@ServletComponentScan
public class HofApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(HofApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(HofApplication.class);
    }


}

