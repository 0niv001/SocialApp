package com.social.socialapp.ui;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route("user")
@PermitAll
public class UserView extends VerticalLayout {

    public UserView() {

        Div content = new Div();

        // Navbar
        HorizontalLayout navBar = new HorizontalLayout();
        navBar.setWidthFull();

        Button homeButton = new Button("Home", VaadinIcon.HOME.create());
        Button chat = new Button("Chat", VaadinIcon.CHAT.create());
        Button profile = new Button("Profile", VaadinIcon.USER.create());
        navBar.add(homeButton, chat, profile);


        //Feed
        content.setSizeFull();

        //footer
        HorizontalLayout footer = new HorizontalLayout();
        footer.setWidthFull();
        footer.add(new HtmlComponent("footer"), new Text("test"));

        add(navBar, content, footer);

    }

}
