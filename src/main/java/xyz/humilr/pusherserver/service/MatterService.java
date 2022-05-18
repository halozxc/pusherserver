package xyz.humilr.pusherserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.humilr.pusherserver.dao.GroupMatterMapper;
import xyz.humilr.pusherserver.dao.MatterFanMapper;
import xyz.humilr.pusherserver.dao.MatterMapper;
import xyz.humilr.pusherserver.pojo.api.UserInfo;
import xyz.humilr.pusherserver.pojo.module.*;
import xyz.humilr.pusherserver.service.GroupService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MatterService {
    private static final Logger logger = LoggerFactory.getLogger(MatterService.class);

    @Autowired
    MatterMapper matterMapper;
    @Autowired
    GroupMatterMapper groupMatterMapper;
    @Autowired
    MatterFanMapper matterFanMapper;
    @Autowired
    GroupService groupService;
    @Autowired
    UserService userService;
    @Autowired
    MatterHistoryService matterHistoryService;
    public boolean publish(UserInfo userInfo, Matter matter) {
        //logger.info(matterMapper.getTime().toString());
        matter.setId(null);
        matter.setManagerUserId(userInfo.getId());
        matter.setPublishDate(new Date());

        return matterMapper.insertSelective(matter) > 0;
    }
    public boolean publishToGroup(UserInfo userInfo, Matter matter, Integer gid) {
        if (!groupService.isUserInGroup(userInfo.getId(), gid)) return false;

        var publishResult = publish(userInfo, matter);
        if (!publishResult) return false;
        Matter t = new Matter();
        var newMatter = matterMapper.getUserLastMatter(userInfo.getId());
        if (newMatter == null) return false;
        return groupMatterMapper.insertSelective(new GroupMatter(gid, newMatter.getId())) > 0;
    }
    public boolean subscribeFromGroup(UserInfo userInfo, Integer mid) {
        if (mid == null) return false;
        MatterFan newMatterFan = new MatterFan(mid, userInfo.getId());
       // newMatterFan.setSourceType(MatterSourceType.GROUP.ordinal());
        MatterFan exist = matterFanMapper.selectOne(newMatterFan);
        //已订阅
        if (exist != null) return true;
        return matterFanMapper.insert(newMatterFan) > 0;
    }
    public boolean subscribeFromUser(UserInfo userInfo, Integer mid) {
        if (mid == null) return false;
        MatterFan newMatterFan = new MatterFan(mid, userInfo.getId());
        newMatterFan.setSourceType(MatterSourceType.USER.ordinal());
        MatterFan exist = matterFanMapper.selectOne(newMatterFan);
        //已订阅
        if (exist != null) return true;
        return matterFanMapper.insertSelective(newMatterFan) > 0;
    }
    public boolean subsCancel(UserInfo userInfo, Integer mid) {
        if (mid == null) return false;
        MatterFan newMatterFan = new MatterFan(mid, userInfo.getId());
        MatterFan exist = matterFanMapper.selectOne(newMatterFan);
        //已订阅 
        if (exist == null) return false;
        return matterFanMapper.deleteByPrimaryKey(exist) > 0;
    }
    public List<Matter> querySubscribed(UserInfo userInfo) {
        MatterFan record = new MatterFan();
        return matterMapper.getUserSubscribeMatter(userInfo.getId());
    }
    public List<Integer> querySubscribedId(UserInfo userInfo) {
        MatterFan record = new MatterFan();
        return matterMapper.getUserSubscribeMatterId(userInfo.getId());
    }
    public List<Matter> queryJoinedGroup(UserInfo userInfo, Date after) {
        var joinedGroups = groupService.queryJoined(userInfo);
        return new ArrayList<>() {{
            for (var gf : joinedGroups) {
                GroupMatter gm = new GroupMatter();
                gm.setGroupId(gf.getGroupId());
                if (groupMatterMapper.selectCount(gm) > 0) {
                    //addAll(groupMatterMapper.select(gm));
                    var gmSelectResult = groupMatterMapper.select(gm);
                    for (var gm1 : gmSelectResult) {
                        Matter matterTmp = new Matter();
                        matterTmp.setId(gm1.getMatterId());
                        matterTmp = matterMapper.selectByPrimaryKey(matterTmp.getId());
                           if (after != null && !matterTmp.getPublishDate().after(after))
                           {continue;}
                        matterTmp.setGroup(groupService.getGroupBaseInfo(gm1.getGroupId()));
                        add(matterTmp);
                    }
                }
            }
        }};
    }
    public  Integer UpdateMatter(Matter matter,Boolean sync){
       var now = new Date();
        if (sync == true){
           var old = matterMapper.selectByPrimaryKey(matter);
           var tmp = new MatterHistory();
           tmp.setId(null);
           tmp.setHostId(old.getId());
           tmp.setPublishDate(old.getPublishDate());
           tmp.setBody(old.getBody());
           tmp.setEndDate(now);
           matterHistoryService.matterHistoryMapper.insertSelective(tmp);
       }else
        {
           var old = matterHistoryService.queryLatestedVersion(matter.getId());
           if (old != null){
               old.setEndDate(now);
               matterHistoryService.matterHistoryMapper.updateByPrimaryKey(old);
        }
        }

        matter.setPublishDate(now);
        return   matterMapper.updateByPrimaryKey(matter);



    }

}
