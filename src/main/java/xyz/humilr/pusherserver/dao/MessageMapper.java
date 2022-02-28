package xyz.humilr.pusherserver.dao;

import org.apache.ibatis.annotations.Mapper;
import xyz.humilr.pusherserver.pojo.module.Matter;
import xyz.humilr.pusherserver.pojo.module.Message;

@Mapper
public interface MessageMapper extends tk.mybatis.mapper.common.Mapper<Message> {
}
