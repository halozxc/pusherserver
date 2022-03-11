package xyz.humilr.pusherserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.humilr.pusherserver.dao.ContactMapper;
import xyz.humilr.pusherserver.dao.GroupFanMapper;
import xyz.humilr.pusherserver.dao.MessageMapper;
import xyz.humilr.pusherserver.dao.UserMapper;
import xyz.humilr.pusherserver.pojo.api.UserInfo;
import xyz.humilr.pusherserver.pojo.module.GroupFan;
import xyz.humilr.pusherserver.pojo.module.Message;

import java.util.Date;

@Service
public class MessageService {

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

    if(message.getDestination_matter_id() != null){

        //send to the group
        if (!groupService.isUserInGroup(userService.queryUserIdByName(message.getSender()), message.getDestination_matter_id())) return false;

    }
    else if(message.getDestination_user()!=null){
        Integer a =  userService.queryUserIdByName(message.getSender());
        Integer b = userService.queryUserIdByName(message.getDestination_user());
           if(!contactService.vertifyContact(a.intValue(),b.intValue())){
          return false;
        }
           message.setPublish_date(new Date());
       return publishToDatabase(message);
    }
        return true;
    }
    private boolean publishToDatabase(Message message){
        message.setId(null);
        return messageMapper.insertSelective(message)>0;
    }

}
