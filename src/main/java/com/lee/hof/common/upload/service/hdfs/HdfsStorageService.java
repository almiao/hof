package com.lee.hof.common.upload.service.hdfs;

import com.zhinantech.common.upload.*;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HdfsStorageService extends StorageService {
    String localStoragePath;

    public HdfsStorageService(Properties properties) {
        if (properties.containsKey("hdfs.url")) {
            this.localStoragePath = properties.getProperty("hdfs.url");
        }
    }

    @Override
    public String getKey(String targetFilename, String originalFilename) {
        return null;
    }

    @Override
    public UploadedFileBean uploadFile(MultipartFile uploadedFile, String targetFilename) throws IOException {
        return uploadFile(uploadedFile.getInputStream(), new FileInfo(uploadedFile), targetFilename);
    }

    @Override
    public UploadedFileBean uploadFile(InputStream inputStream, FileInfo fileInfo, String targetFilename) throws IOException {
        return null;
    }

    @Override
    public UploadedFileBean uploadPartFile(UploadPartFileBean uploadPartFileBean) {
        return null;
    }

    @Override
    public UploadedFileBean mergePartFiles(MergePartFileBean mergePartFileBean) {
        return null;
    }

    @Override
    public InputStream getFile(String file) throws FileNotFoundException {
        String localFilePath = localStoragePath + "/" + file;
        return IOUtils.buffer(new FileInputStream(localFilePath));
    }

    @Override
    public URL getFileUrl(String key) {
        return null;
    }

    @Override
    public String getUploadId(String key) {
        return null;
    }

    @Override
    public void deleteFile(String file) {
        String localFilePath = localStoragePath + "/" + file;
        new File(localFilePath).delete();
    }

    @Override
    public List<UploadedFileBean> listFiles(String prefix) {
        List<UploadedFileBean> uploadedFileBeans = new ArrayList<UploadedFileBean>();

        String localFilePath = localStoragePath + "/" + prefix;
        for (File file : new File(localFilePath).listFiles()) {
            uploadedFileBeans.add(new UploadedFileBean(file.getAbsolutePath().substring(localStoragePath.length()), "local"));
        }

        return uploadedFileBeans;
    }

    @Override
    public String getContext() {
        return localStoragePath;
    }
}
