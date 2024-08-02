package com.sanelee.bigevent.controller;

import com.sanelee.bigevent.pojo.Article;
import com.sanelee.bigevent.pojo.PageBean;
import com.sanelee.bigevent.pojo.Result;
import com.sanelee.bigevent.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }

    //文章列表，条件分页
    @GetMapping("/list")
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state){
        PageBean<Article> pb = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }
    //删除文章
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        articleService.delete(id);
        return Result.success();
    }

}
