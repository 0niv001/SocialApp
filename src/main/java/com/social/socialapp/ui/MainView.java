package com.social.socialapp.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("")
@AnonymousAllowed
public class MainView extends VerticalLayout {
    public MainView() {
        H1 title = new H1("SocialHub");

        // Login Button
        Button signInButton = new Button();
        signInButton.setText("Log In");
        signInButton.addClickListener(event -> signInButton.getUI().ifPresent(ui -> ui.navigate("login")));
        HorizontalLayout navBar = new HorizontalLayout(title, signInButton);
        navBar.setWidthFull();
        navBar.setJustifyContentMode(JustifyContentMode.BETWEEN);


        add(navBar);
    }
}
