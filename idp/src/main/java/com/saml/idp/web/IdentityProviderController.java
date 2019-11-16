package com.saml.idp.web;


import com.saml.idp.model.User;
import com.saml.idp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class IdentityProviderController {
    private static final Log logger =LogFactory.getLog(IdentityProviderController.class);

    @RequestMapping(value = {"/"})
    public String selectProvider() {
        logger.info("Sample IDP Application - Select an SP to log into!");
        return "redirect:/saml/idp/select";
    }

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
