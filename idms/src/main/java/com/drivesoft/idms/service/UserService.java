package com.drivesoft.idms.service;

import com.drivesoft.idms.repository.user.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
