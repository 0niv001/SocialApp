package com.social.socialapp.ui;

import com.social.socialapp.services.UserService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;


@Route("login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends Composite<LoginOverlay> {
    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    public LoginView(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        LoginOverlay loginOverlay = getContent();
        getContent().setOpened(true);
        getContent().setTitle("Login");
        loginOverlay.addLoginListener(event -> {
            String username = event.getUsername();
            String password = event.getPassword();
            System.out.println(username);
            boolean authenticated = authenticate(event.getUsername(), event.getPassword());
            if (authenticated) {
                loginOverlay.close();
                UI.getCurrent().navigate(UserView.class);
            } else {
                loginOverlay.setError(true);
            }
        });

    }
    private boolean authenticate(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return authentication.isAuthenticated();
        } catch (AuthenticationException e) {
            return false; // Authentication failed
        }
    }

}
