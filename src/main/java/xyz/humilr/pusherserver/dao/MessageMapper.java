package xyz.humilr.pusherserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.humilr.pusherserver.pojo.module.Matter;
import xyz.humilr.pusherserver.pojo.module.Message;

import java.util.List;

@Mapper
public interface MessageMapper extends tk.mybatis.mapper.common.Mapper<Message> {
    @Select("select * from message where destination_user = #{uid} or exist ")
    List<Message> queryMessage(String uid);
}
