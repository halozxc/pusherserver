package xyz.humilr.pusherserver.dao;

import org.apache.ibatis.annotations.Select;
import xyz.humilr.pusherserver.pojo.module.Contact;
import xyz.humilr.pusherserver.pojo.module.Message;

public interface ContactMapper extends  tk.mybatis.mapper.common.Mapper<Contact>{
    @Select("select * from contact where user_1 = #{user_1} and user_2 = #{user_2} or user_1 = #{user_2} and user_2 = #{user_1} order by id desc limit 1")
    Contact vertifyContact(Integer user_1,Integer user_2);
}
