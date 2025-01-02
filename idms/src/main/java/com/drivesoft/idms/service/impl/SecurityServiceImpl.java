package com.drivesoft.idms.service.impl;

import com.drivesoft.idms.dao.UserDao;
import com.drivesoft.idms.repository.user.User;
import com.drivesoft.idms.service.SecurityService;
import com.drivesoft.idms.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDao userDao;

    public String authenticateUser(User user) {
        UserDetails authenticatedUser;
        if (!validateUser(user)) {
            log.error("Invalid user or credentials found for user {}",user.getUsername());
            throw new RuntimeException(" Invalid user or credentials found ");
        }
        authenticatedUser = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
        setAuthentication(authenticatedUser);
        return jwtUtil.generateToken(user.getUsername());
    }

    private void setAuthentication(UserDetails userDetails) {
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


    private Boolean validateUser(User sourceUser) {
        User targetUser = userDao.findByUsername(sourceUser.getUsername());
        if (sourceUser.getUsername().equals(targetUser.getUsername())
                && sourceUser.getPassword().equals(targetUser.getPassword())) {

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(targetUser, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean validateToken(String token, User user) {
        try {
            return jwtUtil.validateToken(token, user.getUsername());
        } catch (Exception e) {
            throw new RuntimeException("Invalid or expired token");
        }
    }
}
