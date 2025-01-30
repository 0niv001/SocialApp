package com.social.socialapp.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;
import java.util.List;

@Route("user")
@PermitAll
public class UserView extends VerticalLayout {

    public UserView() {

        add("Welcome to the Social Media Dashboard!");
    }

}
