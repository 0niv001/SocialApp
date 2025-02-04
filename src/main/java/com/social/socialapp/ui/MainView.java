package com.social.socialapp.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.Lumo;
import jakarta.annotation.security.PermitAll;

@Route("/")
@AnonymousAllowed
public class MainView extends VerticalLayout {
    public MainView() {

        //Navbar
        H1 title = new H1("SocialHub");

        Button toggleButton = new Button("Toggle theme variant", click -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList();

            if (themeList.contains(Lumo.DARK)) {
                themeList.remove(Lumo.DARK);
            } else {
                themeList.add(Lumo.DARK);
            }
        });

        // Login Button
        Button signInButton = new Button();
        signInButton.setText("Log In");
        signInButton.addClickListener(event -> signInButton.getUI().ifPresent(ui -> ui.navigate("login")));

        HorizontalLayout navBar = new HorizontalLayout(title, signInButton, toggleButton);
        navBar.setWidthFull();
        navBar.setJustifyContentMode(JustifyContentMode.BETWEEN);

        add(navBar);


    }
}
