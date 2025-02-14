package com.social.socialapp.ui;

import com.social.socialapp.services.UserService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;


@Route("/login")
@AnonymousAllowed
@PreserveOnRefresh
public class LoginView extends VerticalLayout {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public LoginView(UserService userService) {
        this.userService = userService;
        LoginOverlay loginOverlay = new LoginOverlay();
        loginOverlay.setTitle("Creator Spot");
        loginOverlay.setDescription("Work on cool projects with cool people");
        loginOverlay.setForgotPasswordButtonVisible(false);
        loginOverlay.addLoginListener(e -> {
            System.out.println("Login event triggered");
            boolean authenticated = authenticate(e.getUsername(), e.getPassword());
           if (authenticated) {
               loginOverlay.close();
               UI.getCurrent().navigate(UserView.class);
               //getUI().ifPresent(ui -> ui.navigate(UserView.class));
           }
           else {
               loginOverlay.setError(true);
               loginOverlay.showErrorMessage("Login Failed", "Username or password is incorrect");
           }
        });
        loginOverlay.setOpened(true);

        Anchor signUpLink = new Anchor("signup", "Don't have an account? Sign Up");
        signUpLink.getStyle().set("text-align", "center").set("margin-top", "10px");

        add(loginOverlay, signUpLink);
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

/*
public class LoginView extends VerticalLayout {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public LoginView(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;

        setAlignItems(Alignment.CENTER);
        setSpacing(true);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();
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
        login.addClickListener(_ -> {
            String username_check = username.getValue();
            String password_check = password.getValue();
            if (authenticate(username_check, password_check)) {
                UI.getCurrent().navigate("user");

            } else {
                Notification notification = new Notification("Invalid username or password");
                notification.open();
                notification.setPosition(Notification.Position.TOP_CENTER);
            }
        });
        login.addClickShortcut(Key.ENTER);

        //Button githubButton = new Button("GitHub", VaadinIcon.LOCK.create());

        //Signup redirect
        H3 noAccount = new H3("Don't have an account? Click the button to sign up");
        Button signUpRedirect = new Button("Sign Up");
        signUpRedirect.addClickListener(_ -> UI.getCurrent().navigate(SignupView.class));

        VerticalLayout form = new VerticalLayout(username, password, login, noAccount, signUpRedirect);
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

    //TODO: Work with HTTP Session to keep user signed in as they use the site

}

 */
