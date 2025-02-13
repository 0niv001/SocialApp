package com.social.socialapp.ui;

import com.social.socialapp.services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;


@Route("/profile")
//@RolesAllowed("ROLE_USER")
@AnonymousAllowed
public class ProfileView extends VerticalLayout {

    @Autowired
    private UserService userService;

    public ProfileView() {
        setSizeFull();
        setPadding(false);
        addClassName("profile-view");

        // Profile Header
        Div profileHeader = createProfileHeader();

        // Navigation Tabs (Posts, Stories, Connections)
        Tabs navigationTabs = new Tabs(new Tab("Posts"), new Tab("Stories"), new Tab("Connections"));
        navigationTabs.addClassName("profile-tabs");

        // Posts Section
        Div postsSection = createPostGrid();

        // Add everything to the layout
        add(profileHeader, navigationTabs, postsSection);
    }

    private Div createProfileHeader() {
        Div profileHeader = new Div();
        profileHeader.addClassName("profile-header");

        Image profileImage = new Image("https://source.unsplash.com/300x300/?portrait", "Profile Image");
        profileImage.addClassName("profile-image");

        H2 username = new H2("niv_m");
        Paragraph bio = new Paragraph("Java Developer");

        Button editProfile = new Button("✏️");

        profileHeader.add(profileImage, username, bio, editProfile);
        return profileHeader;
    }

    private Div createPostGrid() {
        Div postGrid = new Div();
        postGrid.addClassName("post-grid");

        H3 title = new H3("23 Posts");
        Span viewArchives = new Span("View Archives");
        viewArchives.addClassName("view-archives");

        HorizontalLayout header = new HorizontalLayout(title, viewArchives);
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);

        HorizontalLayout posts = new HorizontalLayout();
        posts.addClassName("posts-container");

        // Sample Images
        posts.add(createPostImage("https://source.unsplash.com/200x200/?fashion"));
        posts.add(createPostImage("https://source.unsplash.com/200x200/?style"));
        posts.add(createPostImage("https://source.unsplash.com/200x200/?model"));
        posts.add(createPostImage("https://source.unsplash.com/200x200/?portrait"));

        postGrid.add(header, posts);
        return postGrid;
    }

    private Div createPostImage(String imageUrl) {
        Div post = new Div();
        post.addClassName("post-item");

        Image postImage = new Image(imageUrl, "Post");
        postImage.addClassName("post-img");

        post.add(postImage);
        return post;
    }
}
