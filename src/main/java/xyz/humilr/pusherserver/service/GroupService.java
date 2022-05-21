package xyz.humilr.pusherserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.humilr.pusherserver.config.JwtProperties;
import xyz.humilr.pusherserver.dao.GroupFanMapper;
import xyz.humilr.pusherserver.dao.GroupManagerMapper;
import xyz.humilr.pusherserver.dao.GroupPusherMapper;
import xyz.humilr.pusherserver.pojo.api.UserInfo;
import xyz.humilr.pusherserver.pojo.module.GroupManager;
import xyz.humilr.pusherserver.pojo.module.GroupPusher;
import xyz.humilr.pusherserver.pojo.module.GroupFan;
import xyz.humilr.pusherserver.pojo.module.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    @Autowired
    GroupPusherMapper groupMapper;
    @Autowired
    GroupFanMapper groupFanMapper;
    //    @Autowired
//    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    GroupManagerMapper groupManagerMapper;

    public boolean create(UserInfo userInfo, String name) {
        if (userInfo == null) {
            return false;
        }

        if (StringUtils.isEmpty(name)) {
            name = userInfo.getUsername() + "创建的群组";
        }

        var group = new GroupPusher();
        group.setGroupname(name);
        group.setCreatorUserId(userInfo.getId());

        var result = groupMapper.insertSelective(group);
        if (result == 0) return false;

        var gp = groupMapper.queryUserLastGroup(userInfo.getId());
        var gf = new GroupFan();
        gf.setUserId(userInfo.getId());
        gf.setGroupId(gp.getId());

        if (!groupFanMapper.select(gf).isEmpty()) {
            return false;
        }
        return groupFanMapper.insert(gf) > 0;
    }

    public boolean join(UserInfo userInfo, Integer gid) {
        if (gid == null) return false;
        var gf = new GroupFan();
        gf.setUserId(userInfo.getId());
        gf.setGroupId(gid);
        var result = groupFanMapper.select(gf);
        //判断是否已经在群里
        if (result.size() > 0) return false;
        return groupFanMapper.insertSelective(gf) > 0;
    }

    public List<GroupFan>queryJoined(UserInfo userInfo){
        GroupFan t = new GroupFan();
        t.setUserId(userInfo.getId());
        return groupFanMapper.select(t);
    }

    public GroupPusher query(Integer gid) {
        return groupMapper.selectByPrimaryKey(new GroupPusher(gid));
    }

    public GroupPusher getGroupBaseInfo(Integer gid){
        var record=new GroupPusher();
        record.setId(gid);
        record=groupMapper.selectByPrimaryKey(record);

        var baseInfo=new GroupPusher();
        baseInfo.setId(record.getId());
        baseInfo.setGroupname(record.getGroupname());
        return baseInfo;
    }

    public List<GroupPusher> getGroupList(UserInfo userInfo) {

        if (userInfo == null) {
            return new ArrayList<GroupPusher>();
        }
//        Example example = new Example.Builder(GroupFan.class)
//                .where(Sqls.custom().andEqualTo("user_id", userInfo.getId()))
//                .build();
        var groupFanRecord = new GroupFan();
        groupFanRecord.setUserId(userInfo.getId());
        var groupIdList = groupFanMapper.select(groupFanRecord);//groupFanMapper.selectByExample(example);
        return new ArrayList<>() {{
            //获得组员基本信息
            for (GroupFan groupFan : groupIdList) {
                var group = new GroupPusher();
                group.setId(groupFan.getGroupId());
                GroupPusher result = groupMapper.selectByPrimaryKey(group);
                //获得详细信息
                result.setFans(new ArrayList<>() {{
                    var gfrecord = new GroupFan();
                    gfrecord.setGroupId(result.getId());
                    var gfs = groupFanMapper.select(gfrecord);
                    for (int i = 0; i < gfs.size(); i++) {
                        var fuid = gfs.get(i).getUserId();
                        User fuser_t = userService.queryUserIgnoreSensitive(fuid);
                        if (fuser_t == null) continue;
                        var fuser = new User(fuser_t.getId(), fuser_t.getUname(), fuser_t.getNickname(),fuser_t.getAvatarId());
                        gfs.get(i).setUserInfo(fuser);
                        add(gfs.get(i));
                    }

                }});
                var cuid = result.getCreatorUserId();
                result.setCreator(userService.queryUserIgnoreSensitive(cuid));
                add(result);
            }
        }};
        //return groupMapper.selectByExample(example);
    }

    public boolean changeGroupName(UserInfo userInfo, GroupPusher group) {
        if (group.getId() == null || StringUtils.isEmpty(group.getGroupname())) return false;
        GroupPusher groupPusher = groupMapper.selectOne(new GroupPusher(group.getId(), userInfo.getId()));
        if (groupPusher == null) {
            return false;
        }
        groupPusher.setGroupname(group.getGroupname());
        return groupMapper.updateByPrimaryKey(groupPusher) > 0;
    }

    public boolean changeDisplayName(UserInfo userInfo, GroupFan fan) {
        if (StringUtils.isEmpty(fan.getDisplayName())) return false;
        //用户不在这个群里
        if (groupFanMapper.selectCount(new GroupFan(userInfo.getId(), fan.getGroupId())) == 0) return false;
        return groupFanMapper.updateDisplayName(fan.getGroupId(), userInfo.getId(), fan.getDisplayName()) > 0;
    }

    public boolean isUserInGroup(Integer uid, Integer gid) {
        GroupFan gfRecord = new GroupFan();
        gfRecord.setUserId(uid);
        gfRecord.setGroupId(gid);
        return groupFanMapper.selectCount(gfRecord)>0;
    }

    public GroupPusher searchGroup(UserInfo userInfo,Integer id){

        if(userInfo != null){
           var tmp =  groupMapper.searchGroupById(id);
           return tmp;
        }
        else {
            return null;
        }

 }

    public Boolean AuthorizeManager(UserInfo userInfo,Integer gid ,String username){
      var userid  = userService.queryUserIdByName(username);

        var  grecord = groupMapper.selectByPrimaryKey(gid);
        if (userInfo.getId() == grecord.getCreatorUserId()) {
          return   groupManagerMapper.insertSelective(new GroupManager(gid,userid)) > 0;

        }
        else {
            return false;
        }
    }
    public List<GroupFan> queryFan(Integer gid){
     return  groupFanMapper.getGroupFan(gid);

    }
    public List<String> queryManager(Integer id){
        var idList =  groupManagerMapper.queryManagerId(id);
        List<String>  result = new ArrayList<String>();
 for (Integer i : idList){
 var str = userService.userMapper.selectByPrimaryKey(i).getUname();
    result.add(str);

 }


         return result;
    }

}
