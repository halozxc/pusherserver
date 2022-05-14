package xyz.humilr.pusherserver.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.humilr.pusherserver.pojo.module.MatterHistory;
import xyz.humilr.pusherserver.service.AuthService;
import xyz.humilr.pusherserver.service.MatterHistoryService;

import java.util.List;

@RestController
@RequestMapping("matterhistory")
public class MatterHistoryController {
@Autowired
    AuthService authService;
    @Autowired
    MatterHistoryService matterHistoryService;

    @RequestMapping("query")
    public ResponseEntity<List<MatterHistory>> queryMatterHistory(@CookieValue(name = "PUSHER_TOKEN")String token,@RequestParam(value = "id",required= true ) Integer id){
    var info =  authService.resolveToken(token);
    if (info != null) {
        return ResponseEntity.ok(matterHistoryService.queryMatterHistoryById(info,id));
    }
    else {
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

}
