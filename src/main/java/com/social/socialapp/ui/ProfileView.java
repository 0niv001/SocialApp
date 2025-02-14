package com.social.socialapp.ui;

import com.social.socialapp.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;


@Route("/profile")
//@RolesAllowed("ROLE_USER")
@AnonymousAllowed
@CssImport("./../frontend/styles/styles.css")
public class ProfileView extends VerticalLayout {

    public ProfileView(UserService userService) {
        setAlignItems(Alignment.CENTER);
        setWidth("70%");

        // Profile Section

        HorizontalLayout profileSection = new HorizontalLayout();
        profileSection.addClassName("profile-section");
        profileSection.setWidthFull();
        profileSection.setAlignItems(Alignment.CENTER);


        Image profilePicture = new Image("https://via.placeholder.com/100", "Profile Picture");
        profilePicture.setWidth("100px");
        profilePicture.setHeight("100px");
        profilePicture.getStyle().set("border-radius", "50%");

        VerticalLayout profileInfo = new VerticalLayout();
        profileInfo.addClassName("profile-info");
        profileInfo.add(new H2("Niv"));
        profileInfo.add(new Paragraph("0niv001 - he/him"));
        profileInfo.add(new Anchor("https://nivesh.tech", "nivesh.tech"));
        profileInfo.setSpacing(false);

        Button editProfileButton = new Button("Edit Profile");
        editProfileButton.addClassName("edit-profile-button");

        profileSection.add(profilePicture, profileInfo, editProfileButton);

        // Recent Projects Section
        H3 recentProjectsTitle = new H3("Recent Projects");
        VerticalLayout projectsSection = new VerticalLayout();
        projectsSection.addClassName("projects-section");
        projectsSection.add(
                createProjectCard("PlaylistMaker", "Scraper that creates a Spotify playlist from Billboard 100.", "Python"),
                createProjectCard("WebScraper", "HackerNews web scraper that finds the top post.", "Python"),
                createProjectCard("Android Malware Detection", "ML model for detecting Android malware.", "Jupyter Notebook")
        );

        // Activity Overview
        H3 activityTitle = new H3("Activity Overview");
        Paragraph activityDescription = new Paragraph("Recent contributions and project updates.");

        // Add everything to the main layout
        add(profileSection, recentProjectsTitle, projectsSection, activityTitle, activityDescription);
    }


    private Div createProjectCard(String title, String description, String tech) {
        Div card = new Div();
        card.setClassName("card");

        card.getStyle()
                .set("border", "1px solid #333")
                .set("border-radius", "8px")
                .set("padding", "10px")
                .set("margin-bottom", "10px");

        H4 projectTitle = new H4(title);
        Paragraph projectDescription = new Paragraph(description);
        Span techTag = new Span(tech);
        techTag.addClassName("tech-tag");
        techTag.getStyle()
                .set("background-color", "#eee")
                .set("padding", "5px")
                .set("border-radius", "4px");

        card.add(projectTitle, projectDescription, techTag);
        return card;
    }

}
