package com.social.socialapp.ui;

import com.social.socialapp.entity.UserEntity;
import com.social.socialapp.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
@Route("chat")
@PermitAll
@PreserveOnRefresh
public class ChatView extends VerticalLayout implements BeforeEnterObserver {
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        UserEntity user = userService.getCurrentUser();
        if (user == null) return;
        ChatView chatView = new ChatView();
    }

    @Autowired
    private UserService userService;

    public ChatView() {
        Button backButton = new Button("Back to UserView", event -> getUI().ifPresent(ui -> ui.navigate("user")));
        add(backButton);
    };


}
