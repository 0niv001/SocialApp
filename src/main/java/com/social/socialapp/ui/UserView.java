package com.social.socialapp.ui;

import com.social.socialapp.entity.UserEntity;
import com.social.socialapp.services.UserService;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Route("user")
@PermitAll
public class UserView extends VerticalLayout implements BeforeEnterObserver {

    @Autowired
    private UserService userService;

    //Homepage for logged in users
    public UserView() {

        //TODO: Make Navbar and Footer reusable
        //TODO: Keep user session once authenticated

        Div content = new Div();

        // TODO: Add username to avatar icon
        //String name = UserEntity.getUsername();


        // Avatar
        Avatar avatar = new Avatar();

        //TODO: Add Profile image to avatar ?pull from MongoDB?
        //avatar.setImage();

        // Navbar
        HorizontalLayout navBar = new HorizontalLayout();
        navBar.setWidthFull();

        Button homeButton = new Button("Home", VaadinIcon.HOME.create());
        homeButton.addClickListener(e -> {
            UI.getCurrent().navigate(UserView.class);});


        Button chat = new Button("Chat", VaadinIcon.CHAT.create());
        chat.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("chat")));


        Button profile = new Button("Profile", VaadinIcon.USER.create());
        Button logoutButton = new Button("Logout", VaadinIcon.SIGN_OUT.create());

        //TODO: Add logout functionality
        //logoutButton.addClickListener(e -> {})


        navBar.add(homeButton, chat, profile,avatar, logoutButton);


        //Feed - What to show?
        content.setSizeFull();

        //Footer
        HorizontalLayout footer = new HorizontalLayout();
        footer.setWidthFull();
        footer.add(new HtmlComponent("footer"), new Text("test"));

        add(navBar, content, footer);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        UserEntity user = userService.getCurrentUser();
    }
}
