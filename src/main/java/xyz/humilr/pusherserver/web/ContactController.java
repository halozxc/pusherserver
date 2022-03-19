package xyz.humilr.pusherserver.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
