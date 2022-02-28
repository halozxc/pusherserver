package xyz.humilr.pusherserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public boolean publish(Message message){
//        if(message.getSender() == null){
//            return  false;
//        }
    if(message.getDestination_matter_id() != null){
        //send to the group
        if (!groupService.isUserInGroup(message.getSender(), message.getDestination_matter_id())) return false;
        message.setId(null);
        return messageMapper.insertSelective(message)>0;
    }
else if(message.getDestination_user_id()!=null){

    }
        return true;
    }


}
