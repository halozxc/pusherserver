package xyz.humilr.pusherserver.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.humilr.pusherserver.pojo.module.COSResponse;
import xyz.humilr.pusherserver.service.AuthService;
import xyz.humilr.pusherserver.service.COSService;

import java.util.Date;

@RestController
@RequestMapping("cos")
public class COSController {
@Autowired
    COSService cosService;
@Autowired
    AuthService authService;

    @GetMapping("test")
   public ResponseEntity<COSResponse> getSignature(@CookieValue("PUSHER_TOKEN") String token){
        var info   =  authService.resolveToken(token);
        if (info != null){
            System.out.println(new Date().getTime());
            return ResponseEntity.ok(cosService.getCOSKey("*"));
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
