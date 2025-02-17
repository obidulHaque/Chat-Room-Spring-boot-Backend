package com.example.RoomChat.controllers;


import com.example.RoomChat.entities.ChatRoom;
import com.example.RoomChat.entities.Message;
import com.example.RoomChat.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalTime;

@Controller
@CrossOrigin("https://chat-room-nextjs-frontend.vercel.app/")
public class ChatController {

    @Autowired
    private RoomRepository roomRepository;

    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public Message sendMessage(@DestinationVariable("roomId") String roomId, Message message) {
        ChatRoom chatRoom = roomRepository.findByRoomId(roomId);

        if (chatRoom == null) {
            throw new RuntimeException("Chat room doesn't exist");
        }

        Message newMessage = new Message();
        newMessage.setSender(message.getSender());
        newMessage.setContent(message.getContent());
        newMessage.setTime(LocalTime.now());

        chatRoom.getMessage().add(newMessage);
        roomRepository.save(chatRoom); // ✅ Save the updated chat room

        return newMessage;
    }

}
