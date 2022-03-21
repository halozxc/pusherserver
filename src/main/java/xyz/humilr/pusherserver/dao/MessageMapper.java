package xyz.humilr.pusherserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.humilr.pusherserver.pojo.module.Matter;
import xyz.humilr.pusherserver.pojo.module.Message;

import java.util.List;

@Mapper
public interface MessageMapper extends tk.mybatis.mapper.common.Mapper<Message> {
    @Select("select * from message mm  where (destination_user = #{uname} or sender = #{uname} or exists (select group_matter.matter_id from user , group_fan ,group_matter where user.uname = #{uname} and user.id = group_fan.user_id and group_fan.group_id = group_matter.group_id and group_matter.matter_id = mm.destination_matter_id)) and mm.publish_date > #{after}")
    List<Message> queryMessage(String uname,String after);
}
