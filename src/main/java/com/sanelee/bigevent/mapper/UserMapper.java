package com.sanelee.bigevent.mapper;

import com.sanelee.bigevent.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    //根据用户名查询用户
    User findByUserName(@Param("username") String username);

    //添加
    void add(@Param("username") String username, @Param("md5String") String md5String);

    //更新
    void update(User user);

    void updateAvatar(String avatarUrl,Integer id);

    void updatePwd(@Param("md5String") String md5String, @Param("id") Integer id);
}
