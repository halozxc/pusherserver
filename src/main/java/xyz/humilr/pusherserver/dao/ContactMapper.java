package xyz.humilr.pusherserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.humilr.pusherserver.pojo.module.Contact;
@Mapper
public interface ContactMapper extends  tk.mybatis.mapper.common.Mapper<Contact>{
    @Select("select * from contact where (user_1 = #{user_1} and user_2 = #{user_2}) or (user_1 = #{user_2} and user_2 = #{user_1}) ")
    Integer  vertifyContact(int user_1,int user_2);
}
