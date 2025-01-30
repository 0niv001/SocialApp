package com.social.socialapp.ui;

import com.social.socialapp.services.ChatService;
import com.social.socialapp.services.UserService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinServlet;

public class ChatView extends VerticalLayout {
    private final ChatService chatService;


    public ChatView() {
        this.chatService = VaadinServlet.getCurrent()
                .getServletContext()
                .getAttribute(ChatService.class);
    }
}
