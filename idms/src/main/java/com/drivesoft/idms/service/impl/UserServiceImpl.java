package com.drivesoft.idms.service.impl;

import com.drivesoft.idms.dao.UserDao;
import com.drivesoft.idms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.drivesoft.idms.repository.user.User;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService,UserService {

    @Autowired
    private UserDao userDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        log.info(" User validated from data base: {}",user);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());

    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return new ArrayList<>();
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
