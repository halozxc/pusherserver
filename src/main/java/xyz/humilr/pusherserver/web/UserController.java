package xyz.humilr.pusherserver.web;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import xyz.humilr.pusherserver.config.JwtProperties;
import xyz.humilr.pusherserver.pojo.api.UserInfo;
import xyz.humilr.pusherserver.pojo.module.User;
import xyz.humilr.pusherserver.service.AuthService;
import xyz.humilr.pusherserver.service.UserService;
import xyz.humilr.pusherserver.utils.CookieUtils;
import xyz.humilr.pusherserver.utils.JsonUtils;
import xyz.humilr.pusherserver.utils.JwtUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    AuthService authService;

    @PostMapping(value = "registerByPhone")
    @ResponseBody
    public ResponseEntity<Void> registerByPhone(@Valid @RequestBody User user) {
        Boolean boo = this.userService.registerByTelephone(user);
        if (boo == null || !boo) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "registerByUsername")
    @ResponseBody
    public ResponseEntity<Void> registerByUsername(@Valid @RequestBody User user) {
        Boolean boo = this.userService.registerByUsername(user);
        if (boo == null || !boo) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("accreditByPhone")
    public ResponseEntity<Void> authenticationByPhone(@Valid @RequestBody User user,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response) {
//        logger.info("进入控制器");
        logger.info("login: " + JsonUtils.serialize(user));
        // 登录校验
        String token = authService.authenticationByPhone(user.getUtel(), user.getUpassword());
        if (StringUtils.isBlank(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // 将token写入cookie,并指定httpOnly为true，防止通过JS获取和修改
        CookieUtils.setCookie(request, response, jwtProperties.getCookieName(),
                token, jwtProperties.getCookieMaxAge(), false);
        return ResponseEntity.ok().build();
    }

    @PostMapping("accreditByUsername")
    public ResponseEntity<Void> authentication(@Valid @RequestBody User user,
                                               HttpServletRequest request,
                                               HttpServletResponse response) {
//        logger.info("进入控制器");
        logger.info("login: " + JsonUtils.serialize(user));
        if (StringUtils.isBlank(user.getUname())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // 登录校验
        String token = authService.authentication(user.getUname(), user.getUpassword());
        if (StringUtils.isBlank(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // 将token写入cookie,并指定httpOnly为true，防止通过JS获取和修改
        CookieUtils.setCookie(request, response, jwtProperties.getCookieName(),
                token, jwtProperties.getCookieMaxAge(), false);
        return ResponseEntity.ok().build();
    }

    @GetMapping("verify")
    public ResponseEntity<User> verify(@CookieValue("PUSHER_TOKEN") String token, HttpServletResponse response, HttpServletRequest request) {
        try {
            UserInfo info = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
            String newToken = JwtUtils.generateToken(info, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            CookieUtils.setCookie(request, response, jwtProperties.getCookieName(), newToken, jwtProperties.getCookieMaxAge());
            return ResponseEntity.ok(userService.queryUser(info.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
