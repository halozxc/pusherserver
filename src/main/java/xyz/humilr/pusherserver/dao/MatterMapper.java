package xyz.humilr.pusherserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.humilr.pusherserver.pojo.module.Matter;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface MatterMapper extends tk.mybatis.mapper.common.Mapper<Matter> {
//    @Select("select now()")
//    Timestamp getTime();
    @Select("select * from matter where manager_user_id=#{uid} order by id desc limit 1")
    Matter getUserLastMatter(Integer uid);
    @Select("select  * from matter mm where manager_user_id = #{uid} or exists (select * from matter_fan where matter_fan.matter_id = mm.id and matter_fan.fan_id = #{uid})")
    List<Matter> getUserSubscribeMatter(Integer uid);
    @Select("select  id from matter mm where  exists (select * from matter_fan where matter_fan.matter_id = mm.id and matter_fan.fan_id = #{uid})")
    List<Integer> getUserSubscribeMatterId(Integer uid);

   // @Select("select  id from matter mm where manager_user_id = #{uid} or exists (select * from matter_fan where matter_fan.matter_id = mm.id and matter_fan.fan_id = #{uid})")
}
