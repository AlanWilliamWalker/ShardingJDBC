package com.example.readwrite.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class PageDto implements Serializable {

    private Integer pageNum = 1;// 当前页面
    private Integer pageSize = 10;// 页面大小


}
