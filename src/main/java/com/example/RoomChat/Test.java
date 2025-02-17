package com.example.RoomChat;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("ok")
    public String ok(){
        return "ok";
    }
}
