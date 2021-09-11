package com.lee.hof;


import com.lee.hof.common.upload.service.local.LocalStorageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class StorageConfig {

    @Value("local.storage.path")
    private String localStoragePath;


    @Bean
    public LocalStorageService localStorageService(){
        Properties properties = new Properties();
        if(StringUtils.isNotBlank(localStoragePath)) {
            properties.setProperty("local.storage.path", localStoragePath);
        }
        return new LocalStorageService(properties);
    }

}
