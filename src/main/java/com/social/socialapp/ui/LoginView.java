package com.social.socialapp.ui;

import com.social.socialapp.services.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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


    //Login Form
    public LoginView(UserService userService) {
        this.userService = userService;
        LoginOverlay loginOverlay = new LoginOverlay();
        loginOverlay.setTitle("Creator Spot");
        loginOverlay.setDescription("Work on cool projects with cool people");
        loginOverlay.setForgotPasswordButtonVisible(false);
        loginOverlay.addLoginListener(e -> {
            System.out.println("Login event triggered");
            // Run authentication method
            boolean authenticated = authenticate(e.getUsername(), e.getPassword());
            if (authenticated) {
                loginOverlay.close();
                UI.getCurrent().navigate(UserView.class);
                //getUI().ifPresent(ui -> ui.navigate(UserView.class));
            } else {
                loginOverlay.setError(true);
                loginOverlay.showErrorMessage("Login Failed", "Username or password is incorrect");
            }
        });
        loginOverlay.setOpened(true);

        //Anchor signUpLink = new Anchor("signup", "Don't have an account? Sign Up");
    }


    // Authentication method
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
        //Signup redirect
        H3 noAccount = new H3("Don't have an account? Click the button to sign up");
        Button signUpRedirect = new Button("Sign Up");
        signUpRedirect.addClickListener(_ -> UI.getCurrent().navigate(SignupView.class));
    //TODO: Work with HTTP Session to keep user signed in as they use the site

}

 */
