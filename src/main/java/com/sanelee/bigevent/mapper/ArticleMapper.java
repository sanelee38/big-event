package com.sanelee.bigevent.mapper;

import com.sanelee.bigevent.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增
    @Insert("insert into article( title, content, cover_img, state, category_id, create_user, create_time, update_time)" +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime});")
    void add(Article article);

    List<Article> list(Integer categoryId, String state, Integer userId);

    void delete(Integer id);
}
