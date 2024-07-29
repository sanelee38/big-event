package com.sanelee.bigevent.controller;

import com.sanelee.bigevent.pojo.Category;
import com.sanelee.bigevent.pojo.Result;
import com.sanelee.bigevent.service.CategoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Category>> list() {
        List<Category> categoryList = categoryService.list();
        return Result.success(categoryList);
    }

    @DeleteMapping("/delete")
    public Result delete(@Param("id") Integer id) {
        categoryService.delete(id);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<Category> detail(@Param("id") Integer id){
        Category category = categoryService.findById(id);
        return Result.success(category);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }

}
