package xyz.humilr.pusherserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.humilr.pusherserver.pojo.module.GroupMatter;

@Mapper
public interface GroupMatterMapper extends tk.mybatis.mapper.common.Mapper<GroupMatter> {
    @Select("select group_id from group_matter where group_matter.matter_id = #{matterid}")
    Integer queryGroupIdFromMatter(Integer matterid);
}
