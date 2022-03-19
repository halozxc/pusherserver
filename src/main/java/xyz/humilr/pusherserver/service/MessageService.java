package xyz.humilr.pusherserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.humilr.pusherserver.dao.*;
import xyz.humilr.pusherserver.pojo.api.UserInfo;
import xyz.humilr.pusherserver.pojo.module.GroupFan;
import xyz.humilr.pusherserver.pojo.module.Message;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MatterService.class);
   @Autowired
    GroupMatterMapper groupMatterMapper;
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    GroupFanMapper groupFanMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    GroupService groupService;
    @Autowired
   ContactService contactService;
    public boolean publish(UserInfo userInfo, Message message){
//        if(message.getSender() == null){
//            return  false;
//        }

    if(message.getDestinationMatteId() != null){

        //send to the group
        if (!groupService.isUserInGroup(userService.queryUserIdByName(message.getSender()), groupMatterMapper.queryGroupIdFromMatter(message.getDestinationMatteId())))
        {
            return false;
        }

    }
    else if(message.getDestinationUser()!=null){
        Integer a =  userService.queryUserIdByName(message.getSender());
        Integer b = userService.queryUserIdByName(message.getDestinationUser());
           if(!contactService.vertifyContact(a,b)){
          return false;
        }


    }
        message.setPublishDate(new Date());
        logger.info(message.toString());
        return publishToDatabase(message);
    }//发布消息
    public List<Message> queryMessage(UserInfo userInfo, String after){
        if (userInfo == null) return null;
        if (after == null) after = "2021-03-13T17:14:11.740+08:00";
        return messageMapper.queryMessage(userInfo.getUsername(),after);
    }

    private boolean publishToDatabase( Message message){

        message.setId(null);
        return messageMapper.insertSelective(message)>0;
    }

}
