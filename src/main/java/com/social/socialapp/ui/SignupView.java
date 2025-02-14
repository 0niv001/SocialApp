package com.social.socialapp.ui;

import com.social.socialapp.entity.UserEntity;
import com.social.socialapp.services.UserService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

@Route("/signup")
@AnonymousAllowed
public class SignupView extends VerticalLayout {
    private UserService userService;
    private AuthenticationManager authenticationManager;


    @Autowired
    public SignupView(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;


        Dialog signupDialog = new Dialog();
        signupDialog.setHeaderTitle("Create a New Account");


        VerticalLayout formLayout = new VerticalLayout();

        // Create form fields for the sign-up form
        TextField usernameField = new TextField("Username");
        PasswordField passwordField = new PasswordField("Password");
        PasswordField confirmPasswordField = new PasswordField("Confirm Password");

        // Create the sign-up button
        Button submitButton = new Button("Sign Up", event -> handleSignUp(usernameField, passwordField, confirmPasswordField));
        submitButton.setSizeFull();
        submitButton.addClickShortcut(Key.ENTER);
        // Layout for the form

        formLayout.add(usernameField, passwordField, confirmPasswordField, submitButton);
        signupDialog.add(formLayout);
        signupDialog.setModal(true);
        signupDialog.setCloseOnOutsideClick(false);
        signupDialog.setCloseOnEsc(false);
        signupDialog.open();
        add(signupDialog);
    }


    // Signup functionality connected to SQL Server DB
    private void handleSignUp(TextField usernameField, PasswordField passwordField, PasswordField confirmPasswordField) {
        String username = usernameField.getValue().trim();
        String password = passwordField.getValue().trim();
        String confirmPassword = confirmPasswordField.getValue().trim();

        // Check empty fields
        if (username.isEmpty()) {
            Notification.show("Username required", 3000, Notification.Position.MIDDLE);
            return;
        }

        if (password.isEmpty()) {
            Notification.show("Password required", 3000, Notification.Position.MIDDLE);
            return;
        }

        if (confirmPassword.isEmpty()) {
            Notification.show("Confirm Password required", 3000, Notification.Position.MIDDLE);
            return;
        }

        // Check for password confirmation
        if (!password.equals(confirmPassword)) {
            Notification.show("Passwords do not match", 3000, Notification.Position.MIDDLE);
            return;
        }

        // Check if username or email already exists
        if (userService.findByUsername(username).isPresent()) {
            Notification.show("Username is already taken", 3000, Notification.Position.MIDDLE);
            return;
        }
        //TODO: SQL Injection - Check SQL QUERIES, Security Analysis plugin

        // Register user
        UserEntity registeredUser = userService.registerUser(username, password);
        if (registeredUser != null) {
            Notification.show("User successfully registered", 3000, Notification.Position.MIDDLE);
            UI.getCurrent().navigate(UserView.class);
        } else {
            Notification.show("Registration failed", 3000, Notification.Position.MIDDLE);
        }
    }
}

