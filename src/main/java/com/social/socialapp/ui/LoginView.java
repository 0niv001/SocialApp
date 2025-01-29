package com.social.socialapp.ui;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.stereotype.Controller;


@Route("login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends Composite<LoginOverlay> {
    public LoginView() {
        getContent().setOpened(true);
        getContent().setTitle("Login");
    }
}
