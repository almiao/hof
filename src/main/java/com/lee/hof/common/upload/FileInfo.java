package com.lee.hof.common.upload;

import org.springframework.web.multipart.MultipartFile;

public class FileInfo {
    private String name;

    private Long size;

    private String contentType;

    public FileInfo(String name, Long size, String contentType) {
        this.name = name;
        this.size = size;
        this.contentType = contentType;
    }

    public FileInfo(MultipartFile uploadedFile) {
        this(uploadedFile.getOriginalFilename(), uploadedFile.getSize(), uploadedFile.getContentType());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
