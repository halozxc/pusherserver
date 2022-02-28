package xyz.humilr.pusherserver.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.humilr.pusherserver.config.JwtProperties;
import xyz.humilr.pusherserver.pojo.api.UserInfo;
import xyz.humilr.pusherserver.pojo.module.User;
import xyz.humilr.pusherserver.utils.JwtUtils;
import xyz.humilr.pusherserver.web.UserController;

@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
//    private UserClient userClient;
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;
    //通过用户名验证
    public String authentication(String username, String password) {

        try {
            // 调用微服务，执行查询
            User user = userService.queryUser(username, password);

            // 如果查询结果为null，则直接返回null
            if (user == null) {
                return null;
            }

            // 如果有查询结果，则生成token
            String token = JwtUtils.generateToken(new UserInfo(user.getId(), user.getUname()),
                    jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String authenticationByPhone(String phone, String password) {

        try {
            // 调用微服务，执行查询
            User user = userService.queryUser(phone, password, true);

            // 如果查询结果为null，则直接返回null
            if (user == null) {
                return null;
            }

            // 如果有查询结果，则生成token
            String token = JwtUtils.generateToken(new UserInfo(user.getId(), user.getUname()),
                    jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserInfo resolveToken(String token) {
        UserInfo info = null;
        try {
            info = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return info;
    }
}