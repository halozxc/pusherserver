package xyz.humilr.pusherserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.humilr.pusherserver.dao.ContactMapper;

@Service
public class ContactService {
    @Autowired
    ContactMapper contactMapper;

    public boolean vertifyContact(Integer user_1,Integer user_2){
        Integer a = contactMapper.vertifyContact(user_1,user_2) ;

        return (a != null);
    }
}
