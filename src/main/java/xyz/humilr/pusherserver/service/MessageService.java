package xyz.humilr.pusherserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.humilr.pusherserver.dao.ContactMapper;
import xyz.humilr.pusherserver.dao.GroupFanMapper;
import xyz.humilr.pusherserver.dao.MessageMapper;
import xyz.humilr.pusherserver.dao.UserMapper;
import xyz.humilr.pusherserver.pojo.module.GroupFan;
import xyz.humilr.pusherserver.pojo.module.Message;

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
    ContactMapper contactMapper;
    public boolean publish(Message message){
//        if(message.getSender() == null){
//            return  false;
//        }
    if(message.getDestination_matter_id() != null){
        //send to the group
        if (!groupService.isUserInGroup(message.getSender(), message.getDestination_matter_id())) return false;
      return publishToDatabase(message);
    }
    else if(message.getDestination_user_id()!=null){
   if(!contactMapper.vertifyContact(message.getSender(),message.getDestination_user_id())){
      return false;
   }
       return publishToDatabase(message);
    }
        return true;
    }
    private boolean publishToDatabase(Message message){
        message.setId(null);
        return messageMapper.insertSelective(message)>0;
    }

}
