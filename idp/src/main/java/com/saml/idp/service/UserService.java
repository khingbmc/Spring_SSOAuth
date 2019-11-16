package com.saml.idp.service;


import com.saml.idp.model.User;
import com.saml.idp.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepositories userRepositories;

    public List<User> getAllUsers(){
        return userRepositories.findAll();
    }

    public void addUser(User user){
        userRepositories.save(user);
    }

    public void deleteUser(Long id) {
        userRepositories.deleteById(id);
    }
}
