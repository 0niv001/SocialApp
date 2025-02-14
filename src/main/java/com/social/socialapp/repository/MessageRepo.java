package com.social.socialapp.repository;

import com.social.socialapp.entity.ChatEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepo extends MongoRepository<ChatEntity, String> {
    //List<ChatEntity> findBySenderAndReceiver(int senderId, String receiverId);
    List<ChatEntity> findByReceiver(String receiverId);
}
