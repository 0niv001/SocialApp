package com.social.socialapp.entity;


import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="messages")
@Getter
@Setter
public class ChatEntity {
    @Id
    private String id;
    private String sender;
    private String receiver;
    private String message;
    private LocalDateTime createdAt;

    public ChatEntity(String username, String messageContent) {
    }
}
