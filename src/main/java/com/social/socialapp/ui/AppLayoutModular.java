package com.social.socialapp.ui;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.theme.lumo.Lumo;

// App layout for modular elements
public class AppLayoutModular extends AppLayout {
    public AppLayoutModular() {

        //NavBar
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
        signInButton.addClickListener(event -> UI.getCurrent().navigate(LoginView.class));

        //Footer


       addToNavbar(title, toggleButton, signInButton);
    }
}
