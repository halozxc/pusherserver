package xyz.humilr.pusherserver.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.humilr.pusherserver.pojo.module.MatterFan;
import xyz.humilr.pusherserver.pojo.module.MatterHistory;

import java.util.List;

@Mapper
public interface MatterHistoryMapper extends tk.mybatis.mapper.common.Mapper<MatterHistory>{
@Select("select * from matter_history where host_id = #{id}")
    List<MatterHistory>queryMatterHistoryById(Integer id);
@Select("select * from matter_history where host_id = #{id}  order by end_date desc limit 1")
    MatterHistory querylatestedVersion(Integer id);
}
