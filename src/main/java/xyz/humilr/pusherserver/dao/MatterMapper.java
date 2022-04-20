package xyz.humilr.pusherserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.humilr.pusherserver.pojo.module.Matter;

import java.sql.Timestamp;
import java.util.Date;
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
    @Update("update matter set headline = #{title},startdate = #{startdate},enddate = #{enddate},loaction = #{location},isergency = #{isergency},body  = #{body},repeatfreq = #{repeat},publish_date = #{publishdate} where id  = #{id}")
    public int updateMatter(@Param("id") Integer id,@Param("body") String body,@Param("title")String title,@Param("location") String loacation,@Param("repeat")Integer repeatCount,@Param("isergencey") Boolean isergency,@Param("startdate") Date startdate,@Param("enddate") Date enddate,@Param("publishdate") Date publishdate);

   // @Select("select  id from matter mm where manager_user_id = #{uid} or exists (select * from matter_fan where matter_fan.matter_id = mm.id and matter_fan.fan_id = #{uid})")
}
