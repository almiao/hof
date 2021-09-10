package com.lee.hof.common.upload;

public class UploadedFileBean {
    private String fileId;

    private String name;

    private String fullpath;

    private long size;

    private String extension;

    private String provider;

    private String type;

    private String uploadId;

    private String partEtag;

    public UploadedFileBean(String fileId, String provider) {
        this.fileId = fileId;
        this.provider = provider;
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getPartEtag() {
        return partEtag;
    }

    public void setPartEtag(String partEtag) {
        this.partEtag = partEtag;
    }
}
