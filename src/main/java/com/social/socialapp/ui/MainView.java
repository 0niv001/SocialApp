package com.social.socialapp.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("")
@AnonymousAllowed
public class MainView extends VerticalLayout {
    public MainView() {
        add(
                new H1("TEST")
        );
    }
}
