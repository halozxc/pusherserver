package xyz.humilr.pusherserver.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class FileService {

    public void logDownload(String name, HttpServletResponse response) throws Exception {
        File file = new File("logs_app" + File.separator + name);

        if (!file.exists()) {
            throw new Exception(name + "文件不存在");
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + name);

        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            OutputStream os = response.getOutputStream();

            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
    }

    public Boolean logUpload(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new Exception("未选择需上传的日志文件");
        }

        String filePath = new File("logs_app").getAbsolutePath();
        File fileUpload = new File(filePath);
        if (!fileUpload.exists()) {
            fileUpload.mkdirs();
        }

        fileUpload = new File(filePath, file.getOriginalFilename());
        if (fileUpload.exists()) {
            throw new Exception("上传的日志文件已存在");
        }

        try {
            file.transferTo(fileUpload);

            return true;
        } catch (IOException e) {
            throw new Exception("上传日志文件到服务器失败：" + e.toString());
        }
    }

}
