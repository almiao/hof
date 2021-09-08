package com.lee.hof.sys.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class FileVO {


    private String name;

    private String url;

    private String ordinal;

    private List<FieldVO> fields;



}
