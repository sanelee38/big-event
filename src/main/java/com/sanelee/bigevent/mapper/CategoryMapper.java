package com.sanelee.bigevent.mapper;


import com.sanelee.bigevent.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //新增分类
    @Insert("insert into category(category_name,category_alias,create_time,create_user,update_time) values (#{categoryName},#{categoryAlias},#{createTime},#{createUser},#{updateTime})")
    void add(Category category);

    @Select("select * from category where create_user=#{userId}")
    List<Category> list(Integer userId);

    @Delete("delete from category where id=#{id}")
    void delete(Integer id);

    @Select("select * from category where id=#{id}")
    Category findById(Integer id);

    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=now() where id=#{id}")
    void update(Category category);
}
