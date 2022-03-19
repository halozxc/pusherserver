package xyz.humilr.pusherserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.humilr.pusherserver.pojo.module.Contact;
import xyz.humilr.pusherserver.pojo.module.User;

import java.util.List;

@Mapper
public interface ContactMapper extends  tk.mybatis.mapper.common.Mapper<Contact>{
    @Select("select * from contact where (user_1 = #{user_1} and user_2 = #{user_2}) or (user_1 = #{user_2} and user_2 = #{user_1}) ")
    Integer  vertifyContact(int user_1,int user_2);
    @Select("select * from user uu where exists (select * from contact where uu.id = contact.user_1 and contact.user_2 = #{user} or uu.id = contact.user_2 and contact.user_1 = #{user})")
    List<User> queryContacts(int user);
}
