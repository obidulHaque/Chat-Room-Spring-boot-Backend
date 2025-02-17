package com.example.RoomChat.repositories;

import com.example.RoomChat.entities.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoomRepository extends MongoRepository<ChatRoom,String> {
    ChatRoom findByRoomId(String roomId);
}
