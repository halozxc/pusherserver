package xyz.humilr.pusherserver.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.humilr.pusherserver.service.FileService;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    FileService fileService;
    @PostMapping(value = "upload")
    public ResponseEntity logUpload(@RequestParam("file") MultipartFile file) throws Exception {
        var result = fileService.logUpload(file);
        if(result){
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping(value = "download/{name}")
    public void logDownload(@PathVariable String name, HttpServletResponse response) throws Exception {
        fileService.logDownload(name, response);
    }

}
