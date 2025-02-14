package com.social.socialapp.ui;

import com.social.socialapp.entity.ChatEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;

@Route("chat")
@AnonymousAllowed
public class ChatView extends VerticalLayout {

    private Div chatArea = new Div();
    private WebSocketStompClient stompClient;
    private StompSession stompSession;

    private String username = SecurityContextHolder.getContext().getAuthentication().getName();

    public ChatView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        chatArea.setSizeFull();
        chatArea.getStyle().set("overflow", "auto");
        TextField messageField = new TextField("Message");
        Button sendButton = new Button("Send");

        sendButton.addClickListener(e -> {
            String message = messageField.getValue();
            messageField.clear();
        });

        connectWebSocket();

        add(chatArea, messageField, sendButton);
    }

    private void connectWebSocket() {
        stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        stompClient.connect("ws://localhost:8080/chat-websocket", new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                stompSession = session;
                stompSession.subscribe("/topic/messages", new StompFrameHandler() {
                    @Override
                    public Type getPayloadType(StompHeaders headers) {
                        return ChatEntity.class;
                    }

                    @Override
                    public void handleFrame(StompHeaders headers, Object payload) {
                        ChatEntity message = (ChatEntity) payload;
                        getUI().ifPresent(ui -> ui.access(() -> {
                            chatArea.add(new Div(message.getSender() + ": " + message.getMessage()));
                        }));
                    }
                });
            }
        });
    }

    private void sendMessage(String messageContent) {
        if (stompSession != null && stompSession.isConnected()) {
            ChatEntity message = new ChatEntity(username, messageContent);
            stompSession.send("/app/chat", message);
        }
    }
}
