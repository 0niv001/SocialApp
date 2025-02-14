package com.social.socialapp.ui;

import com.social.socialapp.entity.UserEntity;
import com.social.socialapp.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.Optional;


@Route("/profile")
//@RolesAllowed("ROLE_USER")
@AnonymousAllowed
@CssImport("./../frontend/styles/styles.css")
public class ProfileView extends VerticalLayout {

    public ProfileView(UserService userService) {
        setAlignItems(Alignment.CENTER);

        String currentUser = "user";

        Optional<UserEntity> currentUserEntity = userService.findByUsername(currentUser);

        if (currentUserEntity.isPresent()) {
            UserEntity userEntity = currentUserEntity.get();

            //Profile Section
            HorizontalLayout profileSection = new HorizontalLayout();
            profileSection.addClassName("profile-section");
            profileSection.setWidthFull();
            profileSection.setAlignItems(Alignment.CENTER);


            //Profile Picture
            Image profilePicture = new Image(userEntity.getProfilePicture(), "Profile Picture");
            profilePicture.setWidth("100px");
            profilePicture.setHeight("100px");
            profilePicture.getStyle().set("border-radius", "50%");


            //Show info from DB
            VerticalLayout profileInfo = new VerticalLayout();
            profileInfo.addClassName("profile-info");
            profileInfo.add(new H2(userEntity.getUsername()));
            profileInfo.add(new Paragraph(userEntity.getName() + " - " + userEntity.getBio()));
            //profileInfo.add(new Anchor(userEntity.getWebsite()));
            profileInfo.setSpacing(false);

            Button editProfileButton = new Button("Edit Profile");
            editProfileButton.addClassName("edit-profile-button");

            profileSection.add(profilePicture, profileInfo, editProfileButton);

            // Recent Projects Section
            H3 recentProjectsTitle = new H3("Recent Projects");
            VerticalLayout projectsSection = new VerticalLayout();
            projectsSection.addClassName("projects-section");

            H3 activityTitle = new H3("Activity Overview");
            Paragraph activityDescription = new Paragraph("Recent contributions and project updates.");


            add(profileSection, recentProjectsTitle, activityTitle, activityDescription);
        }



        /*
        // Add everything to the main layout
        projectsSection


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

         */

    }
}
