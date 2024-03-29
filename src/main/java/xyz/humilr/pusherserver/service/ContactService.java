package xyz.humilr.pusherserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.humilr.pusherserver.dao.ContactMapper;
import xyz.humilr.pusherserver.dao.UserMapper;
import xyz.humilr.pusherserver.pojo.api.UserInfo;
import xyz.humilr.pusherserver.pojo.module.User;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    ContactMapper contactMapper;
    @Autowired
    UserMapper userMapper;
    public boolean vertifyContact(Integer user_1,Integer user_2){
        Integer a = contactMapper.vertifyContact(user_1,user_2) ;

        return (a != null);
    }
    public List<User> queryContact(UserInfo user){
        return contactMapper.queryContacts(user.getId());
    }

    public User searchUser(UserInfo user,Integer uid){
        if(user != null){
            var tmp  = userMapper.selectByPrimaryKey(uid);
            return  tmp;
        }else {
            return null;
        }

    }


}
