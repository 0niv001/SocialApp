package com.social.socialapp.ui;

import com.social.socialapp.entity.UserEntity;
import com.social.socialapp.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Route("/chat")
@RolesAllowed("ROLE_USER")
public class ChatView extends VerticalLayout {
    public ChatView() {
        H1 title = new H1("Chat");
        Button backButton = new Button("Back to UserView", event -> getUI().ifPresent(ui -> ui.navigate("user")));
        add(backButton, title);
    };


}
