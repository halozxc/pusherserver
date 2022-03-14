package xyz.humilr.pusherserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.humilr.pusherserver.pojo.module.GroupFan;

@Mapper
public interface GroupFanMapper extends tk.mybatis.mapper.common.Mapper<GroupFan>{
    @Update("update group_fan set display_name=#{displayName} where group_id=#{groupId} and user_id=#{userId}")
    int updateDisplayName(int groupId,int userId,String displayName);

}
