package xyz.humilr.pusherserver.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.humilr.pusherserver.pojo.api.UserInfo;
import xyz.humilr.pusherserver.pojo.module.User;
import xyz.humilr.pusherserver.service.AuthService;
import xyz.humilr.pusherserver.service.ContactService;

import java.util.List;

@RestController
@RequestMapping("contact")
public class ContactController {
   @Autowired
    ContactService contactService;
   @Autowired
    AuthService authService;
    @GetMapping("query")
    public ResponseEntity<List<User>> querycontacts(@CookieValue("PUSHER_TOKEN")String token){
        var user = authService.resolveToken(token);
        if (user != null){
            return ResponseEntity.ok(contactService.queryContact(user));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("searchContactById")
    public ResponseEntity<User> searchUserById(@CookieValue("PUSHER_TOKEN")String token, @RequestParam("userId") Integer id){
      return ResponseEntity.ok(contactService.searchUser(authService.resolveToken(token),id));

    }

}
