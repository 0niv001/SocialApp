package com.social.socialapp.controller;

import com.social.socialapp.entity.ChatEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatEntity sendMessage(ChatEntity chatMessage) {
        chatMessage.setCreatedAt(LocalDateTime.now());
        return chatMessage;
    }
}
