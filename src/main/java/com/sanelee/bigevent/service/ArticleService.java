package com.sanelee.bigevent.service;

import com.sanelee.bigevent.pojo.Article;
import com.sanelee.bigevent.pojo.PageBean;

public interface ArticleService {
    //新增文章
    void add(Article article);

    //条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    void delete(Integer id);
}
