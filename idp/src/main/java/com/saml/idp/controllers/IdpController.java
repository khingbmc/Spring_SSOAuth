package com.saml.idp.controllers;

import com.saml.idp.model.User;
import com.saml.idp.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class IdpController {

    UserService userService;

    List<User> users = new ArrayList<>(Arrays.asList(
            new User("khingbmc", "172542", "172542")
    ));

    @RequestMapping( "/getallUser")
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }
}
