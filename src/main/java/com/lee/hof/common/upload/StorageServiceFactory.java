package com.lee.hof.common.upload;

import com.zhinantech.common.upload.service.aliyun.AliyunStorageService;
import com.zhinantech.common.upload.service.hdfs.HdfsStorageService;
import com.zhinantech.common.upload.service.local.LocalStorageService;

import java.util.Properties;

public class StorageServiceFactory {
    public static StorageService getStorageService(StorageServceProviders provider) {
        return getStorageService(provider, new Properties());
    }

    public static StorageService getStorageService(StorageServceProviders provider, Properties properties) {
        if (provider == StorageServceProviders.ALIYUN) {
            return new AliyunStorageService(properties);
        } else if (provider == StorageServceProviders.HDFS) {
            return new HdfsStorageService(properties);
        } else if (provider == StorageServceProviders.LOCAL) {
            return new LocalStorageService(properties);
        } else {
            return null;
        }
    }
}
