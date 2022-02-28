package xyz.humilr.pusherserver.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.humilr.pusherserver.pojo.module.Matter;
import xyz.humilr.pusherserver.service.AuthService;
import xyz.humilr.pusherserver.service.MatterService;
import xyz.humilr.pusherserver.service.UserService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("matter")
public class MatterController {
    private static final Logger logger = LoggerFactory.getLogger(MatterController.class);

    @Autowired
    MatterService matterService;
    @Autowired
    AuthService authService;

    @PostMapping("publish/group")
    public ResponseEntity publish2Group(@CookieValue(name = "PUSHER_TOKEN") String token, @RequestBody Matter matter) {
        var pubResult = matterService.publishToGroup(authService.resolveToken(token), matter, matter.getGroupId());
        if (pubResult) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    //    @PostMapping("publish/user")
//    public ResponseEntity publish2User(@CookieValue(name = "PUSHER_TOKEN") String token, @RequestBody Matter matter){
//        var pubResult=matterService.publishToGroup(authService.resolveToken(token),matter, matter.getUserId());
//        if (pubResult)return ResponseEntity.ok().build();
//        else return ResponseEntity.badRequest().build();
//    }
    @PostMapping("subscribe/group")
    public ResponseEntity subsFromGroup(@CookieValue(name = "PUSHER_TOKEN") String token, @RequestBody Matter matter) {
        var subsResult = matterService.subscribeFromGroup(authService.resolveToken(token), matter.getId());
        if (subsResult) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @GetMapping("query")
    public ResponseEntity<List<Matter>> queryMatters(@CookieValue(name = "PUSHER_TOKEN") String token) {
        var queryResult = matterService.querySubscribed(authService.resolveToken(token));
        return ResponseEntity.ok(queryResult);
    }
    @GetMapping("query/group")
    public ResponseEntity<List<Matter>> queryJoinedGroupMatters(@CookieValue(name = "PUSHER_TOKEN") String token, String afterDate) {
        Date after = new Date();
        if (!StringUtils.isEmpty(afterDate))//Date after=new Date(afterDate);{
        {
            after= new Date(LocalTime.parse(afterDate).getSecond());
        }
        var queryResult = matterService.queryJoinedGroup(authService.resolveToken(token),after);
        return ResponseEntity.ok(queryResult);
    }

}
