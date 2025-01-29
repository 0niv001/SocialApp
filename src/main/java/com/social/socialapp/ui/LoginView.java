package com.social.socialapp.ui;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;

@Route("login")
public class LoginView extends Composite<LoginOverlay> {
    public LoginView() {
        getContent().setOpened(true);
        getContent().setTitle("Login");
        getContent().setAction("login");

    }
}
