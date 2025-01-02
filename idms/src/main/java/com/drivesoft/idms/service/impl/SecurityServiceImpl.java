package com.drivesoft.idms.service.impl;

import com.drivesoft.idms.dao.UserDao;
import com.drivesoft.idms.repository.user.User;
import com.drivesoft.idms.service.SecurityService;
import com.drivesoft.idms.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
        authenticatedUser = jwtUtil.setAuthenticatedUser(user);
        return jwtUtil.generateToken(authenticatedUser.getUsername());
    }


    public void verifyLoggedInUserAuthentication(String token,String userName) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated()) {
            throw new SecurityException(" Unauthorized access ");
        }
        // Extract the token (Remove 'Bearer ' prefix if present)
        if (authentication != null && authentication.isAuthenticated()) {
            String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
            //user details validated against logged in user deatils
            User user = new User();
            user.setUsername(userName);
            if (!validateToken(jwtToken, user)) {
                throw new RuntimeException(" Unauthorized access ");
            }
        }

    }

    private final Boolean validateUser(User sourceUser) {
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

    private final Boolean validateToken(String token, User user) {
        try {
            return jwtUtil.validateToken(token, user.getUsername());
        } catch (Exception e) {
            throw new RuntimeException("Invalid or expired token");
        }
    }
}
