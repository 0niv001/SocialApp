package com.social.socialapp.ui;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.RolesAllowed;

@Route("/profile/edit")
@AnonymousAllowed
//@RolesAllowed("ROLE_USER")
public class ProfileEditView extends VerticalLayout {

}
