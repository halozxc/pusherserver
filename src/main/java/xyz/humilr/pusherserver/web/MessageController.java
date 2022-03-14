package xyz.humilr.pusherserver.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.humilr.pusherserver.pojo.module.Message;
import xyz.humilr.pusherserver.service.AuthService;
import xyz.humilr.pusherserver.service.MessageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    AuthService authService;
    @Autowired
    MessageService messageService;
    @PostMapping(value = "sending")
    @ResponseBody
    public ResponseEntity<Void> sendingMessage(@CookieValue(name = "PUSHER_TOKEN")String token,  @RequestBody Message message){
       logger.info("{}",message.toString());
       if( messageService.publish(authService.resolveToken(token), message) ){
           return ResponseEntity.ok().build();
       }else {
           return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
       }

    }
    @GetMapping(value = "query")
    @ResponseBody
    public ResponseEntity<List<Message>> queryMessage(@CookieValue(name = "PUSHER_TOKEN")String token, @RequestParam(value = "afterdate",required= false ) String data){
       var result = messageService.queryMessage(authService.resolveToken(token),data);
       return  ResponseEntity.ok(result);

    }
}
