package xyz.humilr.pusherserver.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.humilr.pusherserver.pojo.module.GroupFan;
import xyz.humilr.pusherserver.pojo.module.GroupPusher;
import xyz.humilr.pusherserver.service.AuthService;
import xyz.humilr.pusherserver.service.GroupService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("group")
public class GroupController {

    @Autowired
    AuthService authService;
    @Autowired
    GroupService groupService;

    @GetMapping("list")
    ResponseEntity<List<GroupPusher>> getGroupList(@CookieValue(name = "PUSHER_TOKEN") String token) {
        return ResponseEntity.ok(groupService.getGroupList(authService.resolveToken(token)));
    }

    @PostMapping("create")
    ResponseEntity createGroup(@CookieValue(name = "PUSHER_TOKEN") String token, @RequestBody GroupPusher group) {
        var createResult = groupService.create(authService.resolveToken(token), group.getGroupname());
        if (createResult) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping("join")
    ResponseEntity joinGroup(@CookieValue(name = "PUSHER_TOKEN") String token, @RequestBody GroupPusher group) {
        var joinResult = groupService.join(authService.resolveToken(token), group.getId());
        if (joinResult) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
  @GetMapping("search")
  ResponseEntity<GroupPusher> searchGroup(@CookieValue(name = "PUSHER_TOKEN") String token,@RequestParam(value = "groupid") Integer gid){
        var userinfo = authService.resolveToken(token);
        var result = groupService.searchGroup(userinfo,gid);

       if (result != null){
           return ResponseEntity.ok(result);
      }
       else {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
  }

    @PostMapping("change/groupName")
    ResponseEntity changGroupName(@CookieValue(name = "PUSHER_TOKEN") String token, @RequestBody GroupPusher group) {
        var changeResult = groupService.changeGroupName(authService.resolveToken(token), group);
        if (changeResult) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
    @PostMapping("change/displayName")
    ResponseEntity changDisplayName(@CookieValue(name = "PUSHER_TOKEN") String token, @RequestBody GroupFan fan) {
        var changeResult = groupService.changeDisplayName(authService.resolveToken(token), fan);
        if (changeResult) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
    @GetMapping("authorize")
    ResponseEntity authorizeManager(@CookieValue(name = "PUSHER_TOKEN") String token,@RequestParam(value = "groupid" ) Integer gid,@RequestParam(value = "username" ) String mid){
        if (groupService.AuthorizeManager(authService.resolveToken(token),gid,mid)) {
            return ResponseEntity.ok().build();
        }else{
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("query/manager")
   ResponseEntity<List<String>> queryManagerId(@RequestParam(value = "groupid" )Integer id ,@CookieValue(name = "PUSHER_TOKEN") String token){
        var info = authService.resolveToken(token);
        if( info != null){
            var result = groupService.queryManager(id);
            var a = 1.0;
            return ResponseEntity.ok(result);
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
