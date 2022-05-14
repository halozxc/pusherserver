package xyz.humilr.pusherserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.humilr.pusherserver.dao.UserMapper;
import xyz.humilr.pusherserver.pojo.module.User;
import xyz.humilr.pusherserver.utils.CodecUtils;

import java.util.Random;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserMapper userMapper;

    public User queryUser(Integer uid) {
        // 查询
        User record = new User();
        record.setId(uid);
        // 校验用户名
        // 用户名密码都正确
        User user = userMapper.selectByPrimaryKey(record);
        if (user == null) return null;
        record.setUname(user.getUname());
        record.setUtel(user.getUtel());
        record.setNickname(user.getNickname());
        record.setAvatarId(user.getAvatarId());
        return record;
//        return userMapper.selectOne(record);
    }

    public User queryUserIgnoreSensitive(Integer uid) {
        // 查询
        User record = new User();
        record.setId(uid);
        // 校验用户名
        // 用户名密码都正确
        User user = userMapper.selectByPrimaryKey(record);
        if (user == null) return null;
        record.setUname(user.getUname());
        record.setNickname(user.getNickname());
        record.setAvatarId(user.getAvatarId());
        return record;
//        return userMapper.selectOne(record);
    }

    public User queryUser(String username, String password) {
        // 查询
        User record = new User();
        record.setUname(username);
        User user = userMapper.selectOne(record);
        // 校验用户名
        if (user == null) {
            return null;
        }
        // 校验密码
        if (!user.getUpassword().equals(CodecUtils.md5Hex(password, user.getSalt()))) {
            return null;
        }
        // 用户名密码都正确
        return user;
    }

    public User queryUser(String phone, String password, boolean isPhone) {
        // 查询
        User record = new User();
        record.setUtel(phone);
        User user = userMapper.selectOne(record);
        // 校验用户名
        if (user == null) {
            return null;
        }
        // 校验密码
        if (!user.getUpassword().equals(CodecUtils.md5Hex(password, user.getSalt()))) {
            return null;
        }
        // 用户名密码都正确
        return user;
    }

    public Boolean registerByUsername(User user) {
        logger.info("用户" + user.getUname() + "预注册");
        User temp = new User();
        temp.setUname(user.getUname());
        if (userMapper.selectCount(temp) > 0) return false;
        user.setId(null);
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        user.setUpassword(CodecUtils.md5Hex(user.getUpassword(), salt));
        user.setNickname(user.getUname());
        user.setAvatarId(new Random().nextInt() % 15);
        userMapper.insertSelective(user);
        return true;
    }

    public Boolean registerByTelephone(User user) {
        logger.info("用户" + user.getUname() + "预注册");
        User newUser = new User();
        newUser.setUtel(user.getUtel());
        if (userMapper.selectCount(newUser) > 0) return false;
        user.setId(null);
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        user.setUpassword(CodecUtils.md5Hex(user.getUpassword(), salt));
        user.setNickname(user.getUtel());
        user.setAvatarId(Math.abs(new Random().nextInt() % 18));
        var insertResult = userMapper.insertSelective(user);
        if (insertResult == 0) return false;
        newUser = userMapper.selectOne(newUser);
        if (newUser == null) return false;
        newUser.setUname("pusher_" + newUser.getId());
        userMapper.updateByPrimaryKeySelective(newUser);

        return true;
    }
public Integer queryUserIdByName(String uname)
{
    return userMapper.queryuserIdByName(uname);
}
}
