package com.lee.hof.common.upload.service.local;

import com.lee.hof.common.upload.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class LocalStorageService extends StorageService {
    private static final String LOCAL_PROVIDER_NAME = "local";

    public String localStoragePath;

    public LocalStorageService(Properties properties) {
        if (properties.containsKey("local.storage.path")) {
            this.localStoragePath = properties.getProperty("local.storage.path");
        } else {
            this.localStoragePath = "/opt/springcloud/uploads";
        }

        File localStorageFile = new File(this.localStoragePath);
        if (!localStorageFile.exists()) {
            localStorageFile.mkdirs();
        }
    }

    public String getLocalStoragePath() {
        return localStoragePath;
    }

    @Override
    public String getKey(String targetFilename, String originalFilename) {
        return null;
    }

    @Override
    public UploadedFileBean uploadFile(MultipartFile uploadedFile, String targetFilename) throws IOException {
        String uuid = UUID.randomUUID().toString().substring(0,6);
        if (StringUtils.isEmpty(targetFilename)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            targetFilename = simpleDateFormat.format(Calendar.getInstance().getTime())+"_"+ uuid+ "/" + uploadedFile.getOriginalFilename();
        }

        targetFilename = targetFilename.replaceAll("^[\\/\\\\]+", "");

        return uploadFile(uploadedFile.getInputStream(), new FileInfo(uploadedFile), targetFilename);
    }

    @Override
    public UploadedFileBean uploadFile(InputStream inputStream, FileInfo fileInfo, String targetFilename) throws IOException {
        String localFilename = localStoragePath + "/" + targetFilename;
        FileUtils.forceMkdir(new File(localFilename).getParentFile());

        org.apache.commons.io.IOUtils.copy(inputStream, new FileOutputStream(localFilename));

        UploadedFileBean uploadedFileBean = new UploadedFileBean(targetFilename, LOCAL_PROVIDER_NAME);
        uploadedFileBean.setName(fileInfo.getName());
        uploadedFileBean.setSize(fileInfo.getSize());
        uploadedFileBean.setType(fileInfo.getContentType());
        uploadedFileBean.setExtension(FilenameUtils.getExtension(fileInfo.getName()));
        uploadedFileBean.setFullPath(targetFilename);
        uploadedFileBean.setFileId(UUID.randomUUID().toString());

        return uploadedFileBean;
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
    public UploadedFileBean uploadPartFile(UploadPartFileBean uploadPartFileBean) {
        return null;
    }

    @Override
    public UploadedFileBean mergePartFiles(MergePartFileBean mergePartFileBean) {
        return null;
    }


    @Override
    public UploadedFileBean uploadFile(MultipartFile uploadedFile) throws IOException {
        return super.uploadFile(uploadedFile);
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
        for (File file : Objects.requireNonNull(new File(localFilePath).listFiles())) {
            uploadedFileBeans.add(new UploadedFileBean(file.getAbsolutePath().substring(localStoragePath.length()), LOCAL_PROVIDER_NAME));
        }

        return uploadedFileBeans;
    }

    @Override
    public String getContext() {
        return localStoragePath;
    }
}
