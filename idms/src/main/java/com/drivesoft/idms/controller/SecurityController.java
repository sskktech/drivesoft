package com.drivesoft.idms.controller;

import com.drivesoft.idms.repository.user.User;
import com.drivesoft.idms.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/idms/api")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return securityService.authenticateUser(user);
    }

    public void setAuthentication(UserDetails userDetails) {
        // Create an Authentication object
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities() // Set roles/authorities
                );

        // Set the Authentication object in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
