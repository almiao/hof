package com.lee.hof.common.upload;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public abstract class StorageService {

    public abstract String getKey(String targetFilename, String originalFilename);

    /**
     * 获取文件存储的上下文
     *
     * @return
     */
    public abstract String getContext();

    /**
     * 获取文件
     *
     * @param key
     * @return
     * @throws FileNotFoundException
     */
    public abstract InputStream getFile(String key) throws FileNotFoundException;

    /**
     * 获取文件链接
     *
     * @param key
     * @return
     */
    public abstract URL getFileUrl(String key);


    /**
     * 获取整个文件所对应的唯一主标识
     *
     * @return
     */
    public abstract String getUploadId(String key);

    /**
     * 上传分块文件
     *
     * @return 返回块所对应的唯一标识， 客户端存下需存下此字符串， 作为块的标识！
     */
    public abstract UploadedFileBean uploadPartFile(UploadPartFileBean uploadPartFileBean);


    /**
     * 合并分块文件
     *
     * @return
     */
    public abstract UploadedFileBean mergePartFiles(MergePartFileBean mergePartFileBean);


    /**
     * 删除文件
     *
     * @param key
     */
    public abstract void deleteFile(String key);

    /**
     * 获取某路径下所有文件
     *
     * @param prefix
     * @return
     */
    public abstract List<UploadedFileBean> listFiles(String prefix);

    /**
     * 上传文件, 系统会根据当前日期生成一个路径
     *
     * @param uploadedFile
     * @return
     * @throws IOException
     */
    public UploadedFileBean uploadFile(MultipartFile uploadedFile) throws IOException {
        return uploadFile(uploadedFile, null);
    }

    /**
     * 上传文件
     *
     * @param uploadedFile
     * @param targetFilename OSS 存储的绝对位置，如果不传此文件名，系统会根据当前日期生成一个
     * @return
     * @throws IOException
     */
    public UploadedFileBean uploadFile(MultipartFile uploadedFile, String targetFilename) throws IOException {
        String uuid = UUID.randomUUID().toString().substring(0,6);
        if (StringUtils.isEmpty(targetFilename)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            targetFilename = simpleDateFormat.format(Calendar.getInstance().getTime())+"_"+ uuid + "/" + uploadedFile.getOriginalFilename();
        }
        return uploadFile(uploadedFile.getInputStream(), new FileInfo(uploadedFile), targetFilename);
    }

    public abstract UploadedFileBean uploadFile(InputStream inputStream, FileInfo fileInfo, String targetFilename) throws IOException;

}
