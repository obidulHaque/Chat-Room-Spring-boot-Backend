package com.example.RoomChat.controllers;


import com.example.RoomChat.entities.Message;
import com.example.RoomChat.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("chatRoom")
@CrossOrigin("https://chat-room-nextjs-frontend.vercel.app/")
public class RoomController {
    @Autowired
    private ChatRoomService chatRoomService;

    @GetMapping("{roomId}")
    public ResponseEntity<?> getRoom(@PathVariable("roomId") String roomId){
       return chatRoomService.getRoom(roomId);

    }
    @PostMapping
    public ResponseEntity<?> createChatRoom(@RequestBody String roomId){
        return chatRoomService.createRoom(roomId);
    }
    @GetMapping("{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable("roomId") String roomId,
                                                     @RequestParam(value = "page",defaultValue = "0",required = false) int page,
                                                     @RequestParam(value = "size",defaultValue = "0",required = false)int size){
         return chatRoomService.getMessages(roomId,page,size);
    }
}
