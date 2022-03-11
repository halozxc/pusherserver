package xyz.humilr.pusherserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.humilr.pusherserver.pojo.module.GroupPusher;
import xyz.humilr.pusherserver.pojo.module.User;

@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {
    @Select("select * from user where creator_user_id=#{uid} order by id desc limit 1")
    GroupPusher queryLastUser(int uid);
    @Select("select * from user where uname=#{uname} order by id desc limit 1")
    Integer queryuserIdByName(String uname);
}