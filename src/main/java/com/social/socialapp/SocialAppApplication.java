package com.social.socialapp;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

@SpringBootApplication
public class SocialAppApplication extends VaadinWebSecurity {

    public static void main(String[] args) {
        SpringApplication.run(SocialAppApplication.class, args);
    }

}
