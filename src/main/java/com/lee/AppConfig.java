package com.lee;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.lee.hof.sys")
@EnableAspectJAutoProxy
public class AppConfig {
}

