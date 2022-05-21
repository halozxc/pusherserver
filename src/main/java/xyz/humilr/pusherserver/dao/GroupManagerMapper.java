package xyz.humilr.pusherserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.humilr.pusherserver.pojo.module.GroupManager;
import xyz.humilr.pusherserver.pojo.module.GroupMatter;

import java.util.List;

@Mapper
public interface GroupManagerMapper extends tk.mybatis.mapper.common.Mapper<GroupManager>{
@Select("select manager_user_id from group_manager where group_id = #{id} ")
    List<Integer> queryManagerId(Integer id);

}
