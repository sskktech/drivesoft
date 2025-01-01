package com.drivesoft.idms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.drivesoft.idms.repository.user.User;


public interface UserDao extends JpaRepository<User, String> {
    User findByUsername(String username);
}