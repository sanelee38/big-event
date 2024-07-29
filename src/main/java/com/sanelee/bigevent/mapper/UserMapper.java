package com.sanelee.bigevent.mapper;

import com.sanelee.bigevent.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from user where username=#{username}")
    User findByUserName(@Param("username") String username);

    //添加
    @Insert("insert into user(username, password, create_time, update_time) values (#{username},#{md5String},now(),now())")
    void add(@Param("username") String username, @Param("md5String") String md5String);

    //更新
    @Update("update user set nickname = #{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    @Update("update user set user_pic = #{avatarUrl},update_time=now() where id=#{id}" )
    void updateAvatar(String avatarUrl,Integer id);

    @Update("update user set password = #{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);
}
