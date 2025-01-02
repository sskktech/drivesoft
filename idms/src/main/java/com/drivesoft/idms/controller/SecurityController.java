package com.drivesoft.idms.controller;

import com.drivesoft.idms.repository.user.User;
import com.drivesoft.idms.service.SecurityService;
import com.drivesoft.idms.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/idms/api")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/Login")
    public String login(@RequestBody User user) {
        String authenticatedUser = securityService.authenticateUser(user);
        jwtUtil.setAuthenticatedUser(user);
        return authenticatedUser;
    }


}
