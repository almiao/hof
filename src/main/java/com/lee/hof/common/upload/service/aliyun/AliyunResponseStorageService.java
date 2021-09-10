package com.lee.hof.common.upload.service.aliyun;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AliyunResponseStorageService {
    private final static String ALIYUN_PROVIDER_NAME = "aliyun";

    protected String bucketName;

    protected String endpoint;

    protected String accessKeyId;

    protected String accessKeySecret;

    protected OSSClient ossClient;

    public AliyunResponseStorageService(Properties properties) {
        if (properties.containsKey("aliyun.oss.bucketName")) {
            this.bucketName = properties.getProperty("aliyun.oss.bucketName");
        }

        if (properties.containsKey("aliyun.oss.endpoint")) {
            this.endpoint = properties.getProperty("aliyun.oss.endpoint");
        }

        if (properties.containsKey("aliyun.oss.accessKeyId")) {
            this.accessKeyId = properties.getProperty("aliyun.oss.accessKeyId");
        }

        if (properties.containsKey("aliyun.oss.accessKeySecret")) {
            this.accessKeySecret = properties.getProperty("aliyun.oss.accessKeySecret");
        }
    }

    public PutObjectResult uploadResponse(InputStream inputStream, String targetFilename) {
        OSSClient ossClient = getOSSClient();
        return ossClient.putObject(bucketName, targetFilename, inputStream);
    }

    public String getResponse(String responseUuid) throws IOException {
        OSSClient ossClient = getOSSClient();
        OSSObject ossObject = ossClient.getObject(bucketName, responseUuid);
        InputStream inputStream = ossObject.getObjectContent();
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }

    public String getContext() {
        return bucketName;
    }

    public synchronized OSSClient getOSSClient() {
        if (ossClient == null) {
            CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
            ossClient = new OSSClient(endpoint, credentialsProvider, null);
        }

        return ossClient;
    }
}
