package com.sanelee.bigevent.pojo;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

@Data
public class Article {
/**
     * ID
     */
    private Integer id;
/**
     * 文章标题
     */
    private String title;
/**
     * 文章内容
     */
    private String content;
/**
     * 文章封面
     */
    private String coverImg;
/**
     * 文章状态：只能是【已发布】或者【草稿】
     */
    private String state;
/**
     * 文章分类ID
     */
    private Integer categoryId;
/**
     * 创建人ID
     */
    private Integer createUser;
/**
     * 创建时间
     */
    private Date createTime;
/**
     * 修改时间
     */
    private Date updateTime;
}

