package xyz.humilr.pusherserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.humilr.pusherserver.pojo.module.GroupPusher;

@Mapper
public interface GroupPusherMapper extends tk.mybatis.mapper.common.Mapper<GroupPusher> {

    @Select("select * from group_pusher where creator_user_id=#{uid} order by id desc limit 1")
    GroupPusher queryUserLastGroup(int uid);
}
