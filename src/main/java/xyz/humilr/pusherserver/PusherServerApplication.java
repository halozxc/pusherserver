package xyz.humilr.pusherserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class PusherServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PusherServerApplication.class, args);
        System.out.println("i'm working");
    }

}
