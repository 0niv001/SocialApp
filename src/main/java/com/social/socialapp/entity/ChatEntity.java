package com.social.socialapp.entity;

import jakarta.persistence.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

//MongoDB chat Object
/* TODO:
- Connect entitity to Mongo
 */

//@Document(collection = "Messages")
public class ChatEntity {
    @Id
    private String id;
    private String sender;
    private String receiver;
    private String message;
    private LocalDateTime sentAt;

    public ChatEntity(String sender, String receiver, String message, LocalDateTime sentAt) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.sentAt = sentAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
