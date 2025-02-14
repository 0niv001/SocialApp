package com.social.socialapp.ui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("/")
@AnonymousAllowed
@CssImport("./../frontend/styles/styles.css")
public class MainView extends VerticalLayout {
    public MainView() {
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        // Navigation Bar
        HorizontalLayout navbar = new HorizontalLayout();
        navbar.setWidthFull();
        navbar.addClassName("navbar");
        Button signIn = new Button("Sign In");
        signIn.addClassName("nav-item");
        signIn.addClickListener(e -> {
            signIn.getUI().ifPresent(ui -> {
                ui.navigate(LoginView.class);
            });
        });
        navbar.add(new H2("Creator Spot"), signIn);


        // Hero Section
        Div heroSection = new Div();
        heroSection.addClassName("hero");
        heroSection.add(new H1("Where Creators Connect"));
        heroSection.add(new Paragraph("Join a network of creators and work with great talents on amazing new " +
                "projects"));

        Button getStarted = new Button("Sign Up");
        getStarted.addClickListener(e -> {
            UI.getCurrent().navigate(SignupView.class);
        });
        getStarted.addClickShortcut(Key.ENTER);
        getStarted.addClassName("cta-button");
        heroSection.add(getStarted);


        add(navbar, heroSection);
    }
}
