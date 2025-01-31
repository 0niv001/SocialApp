package com.social.socialapp.ui;

import com.social.socialapp.services.UserService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;


@Route("login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout {
    private UserService userService;

    private AuthenticationManager authenticationManager;

    @Autowired
    public LoginView(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        // Username Field
        TextField username = new TextField("Username");
        username.setPlaceholder("Username");
        username.setRequiredIndicatorVisible(true);

        //Password Field
        PasswordField password = new PasswordField("Password");
        password.setPlaceholder("Password");
        password.setRequiredIndicatorVisible(true);

        //Login Button
        Button login = new Button("Login");
        login.addClickListener(e -> {
            String username_check = username.getValue();
            String password_check = password.getValue();
            System.out.println(username);
            boolean authenticated = authenticate(username_check, password_check);
            if (authenticated) {
                UI.getCurrent().navigate(UserView.class);
            } else {
                Notification notification = new Notification("Invalid username or password");
                notification.open();
                notification.setPosition(Notification.Position.TOP_CENTER);
            }
        });
        login.addClickShortcut(Key.ENTER);

        Button githubButton = new Button("GitHub",VaadinIcon.LOCK.create());

        //TODO: Add GitHub OAuth2 - Should I store this in the DB?
        /*
        githubButton.addClickListener(e -> {
            break;
        })
         */


        FormLayout form = new FormLayout(username, password, login, githubButton);
        add(form);
    }

    // Authenticate existing user from SQL server
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
