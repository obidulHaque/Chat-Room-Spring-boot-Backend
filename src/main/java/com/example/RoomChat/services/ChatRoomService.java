package com.example.RoomChat.services;

import com.example.RoomChat.entities.ChatRoom;
import com.example.RoomChat.entities.Message;
import com.example.RoomChat.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomService {
    @Autowired
    private RoomRepository roomRepository;

    public ResponseEntity<?> getRoom(String roomId){
        ChatRoom room = roomRepository.findByRoomId(roomId);
        if (room==null){
            return ResponseEntity.badRequest().body("Room Dosen't Exist");
        }
        return ResponseEntity.status(201).body(room);
    }

    public ResponseEntity<?> createRoom(String roomId){
        ChatRoom room = roomRepository.findByRoomId(roomId);
        if (room == null) {
            ChatRoom newRoom = new ChatRoom();
            newRoom.setRoomId(roomId);
            roomRepository.save(newRoom);
            return ResponseEntity.status(201).body(newRoom);
        }

        return ResponseEntity.badRequest().body("Room already exist");
    }
    public ResponseEntity<List<Message>> getMessages (String roomId,int page,int size){
        ChatRoom room=roomRepository.findByRoomId(roomId);
        if (room ==null){
            return ResponseEntity.badRequest().build();
        }
        List<Message> messages=room.getMessage();
        return ResponseEntity.ok(messages);
    }
}
