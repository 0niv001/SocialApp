package com.social.socialapp.ui;

import com.social.socialapp.services.UserService;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;


@Route("user")
@PermitAll
public class UserView extends VerticalLayout {

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
        //Avatar avatar = new Avatar();


        // Navbar
        HorizontalLayout navBar = new HorizontalLayout();
        navBar.setClassName("navbar");
        navBar.setWidthFull();

        Button home = new Button("Home", VaadinIcon.HOME.create());
        home.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("user")));

        Button friends = new Button("Friends", VaadinIcon.CHAT.create());
        friends.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("#")));

        Button chat = new Button("Chat", VaadinIcon.CHAT.create());
        chat.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("chat")));

        Button profile = new Button("Profile", VaadinIcon.USER.create());
        profile.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("profile")));

        Button logoutButton = new Button("Logout", VaadinIcon.SIGN_OUT.create());
        logoutButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate("/"));
            Notification.show("Logout successful", 1000, Notification.Position.MIDDLE);
        });

        ;


        navBar.add(home, friends, chat, profile, logoutButton);

        //Footer
        HorizontalLayout footer = new HorizontalLayout();
        footer.setWidthFull();
        footer.add(new HtmlComponent("footer"), new Text("test"));


        add(navBar, content, footer);
    }
}
