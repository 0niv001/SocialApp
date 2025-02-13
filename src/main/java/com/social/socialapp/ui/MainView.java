package com.social.socialapp.ui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.Lumo;
import jakarta.annotation.security.PermitAll;

import static com.vaadin.uitest.parser.Parser.views;

@Route("/")
@AnonymousAllowed
@CssImport("./../frontend/styles/styles.css")
public class MainView extends VerticalLayout {
    public MainView() {

        setClassName("main-view");
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        // Navigation Bar
        HorizontalLayout navbar = new HorizontalLayout();
        navbar.setWidthFull();
        navbar.addClassName("navbar");
        Button signIn = new Button("Sign In");
        signIn.addClickListener(e -> {signIn.getUI().ifPresent(ui -> {ui.navigate(LoginView.class);});});
        navbar.add(new H2("Creator Spot"), signIn);


        // Hero Section
        Div heroSection = new Div();
        heroSection.addClassName("hero");
        heroSection.add(new H1("Where Creators Connect"));
        heroSection.add(new Paragraph("Creators can easily filter, find, and connect with creators big or small to " +
                "collaborate and create amazing new projects together"));

        Button getStarted = new Button("Sign Up");
        getStarted.addClickListener(e -> {UI.getCurrent().navigate(SignupView.class);});
        getStarted.addClickShortcut(Key.ENTER);
        getStarted.addClassName("cta-button");
        heroSection.add(getStarted);

        // Creator Showcase
        Div creatorsSection = new Div();
        creatorsSection.addClassName("creators-section");

        creatorsSection.add(new H2("Work with new people on cool projects"));

        HorizontalLayout creatorsList = new HorizontalLayout();
        creatorsList.addClassName("creators-list");

        creatorsList.add(createCreatorCard("Devon Lane"));
        creatorsList.add(createCreatorCard("Robert Fox"));
        creatorsList.add(createCreatorCard("Floyd Miles"));

        creatorsSection.add(creatorsList);

        add(navbar, heroSection, creatorsSection);
    }

    private VerticalLayout createCreatorCard(String name) {
        VerticalLayout card = new VerticalLayout();
        card.addClassName("creator-card");
        card.add(new H3(name));
        return card;


        // Light and dark mode toggle
        /*
        Button toggleButton = new Button("Toggle theme variant", click -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList();

            if (themeList.contains(Lumo.DARK)) {
                themeList.remove(Lumo.DARK);
            } else {
                themeList.add(Lumo.DARK);
            }
        });
         */


    }
}
