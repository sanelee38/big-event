package com.sanelee.bigevent.service;

import com.sanelee.bigevent.pojo.Category;

import java.util.List;

public interface CategoryService {
    //新增分类
    void add(Category category);

    List<Category> list();

    void delete(Integer id);

    Category findById(Integer id);

    void update(Category category);
}
