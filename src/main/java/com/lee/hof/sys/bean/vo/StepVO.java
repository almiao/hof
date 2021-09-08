package com.lee.hof.sys.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class StepVO {


    private String stepName;

    private String description;

    private String ordinal;

    private List<FileVO> files;




}
