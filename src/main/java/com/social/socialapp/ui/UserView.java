package com.social.socialapp.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

@Route("user")
@PermitAll
public class UserView extends VerticalLayout {

    public UserView() {
        add(new H1("Welcome User"));
    }
}
