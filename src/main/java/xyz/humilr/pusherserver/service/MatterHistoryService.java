package xyz.humilr.pusherserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.humilr.pusherserver.dao.MatterHistoryMapper;
import xyz.humilr.pusherserver.pojo.api.UserInfo;
import xyz.humilr.pusherserver.pojo.module.Matter;
import xyz.humilr.pusherserver.pojo.module.MatterHistory;

import java.util.List;

@Service
public class MatterHistoryService {
    @Autowired
    MatterHistoryMapper matterHistoryMapper;
public List<MatterHistory> queryMatterHistoryById(UserInfo info, Integer id){
    if (info == null) {
        return null;
    }
    else{
        return matterHistoryMapper.queryMatterHistoryById(id);
    }
}
}
