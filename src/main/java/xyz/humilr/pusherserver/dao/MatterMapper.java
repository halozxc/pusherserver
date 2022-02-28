package xyz.humilr.pusherserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.humilr.pusherserver.pojo.module.Matter;

import java.sql.Timestamp;

@Mapper
public interface MatterMapper extends tk.mybatis.mapper.common.Mapper<Matter> {
//    @Select("select now()")
//    Timestamp getTime();
    @Select("select * from matter where manager_user_id=#{uid} order by id desc limit 1")
    Matter getUserLastMatter(Integer uid);
    
}
