package com.drivesoft.idms.service;

import com.drivesoft.idms.repository.user.User;

public interface SecurityService {

    String authenticateUser(User user);
    Boolean validateToken(String token, User user);
}
