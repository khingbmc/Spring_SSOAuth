package com.saml.idp.web;


import com.saml.idp.model.User;
import com.saml.idp.service.UserService;
import com.saml.idp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        return "redirect:/saml/idp/select";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

//    UserService userService;

//    List<User> users = new ArrayList<>(Arrays.asList(
//            new User("khingbmc", "172542", "172542")
//    ));

//    @RequestMapping( "/getallUser")
//    public List<User> getAllUser() {
//        return userService.getAllUsers();
//    }
//
//    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
//    public void addUser(@RequestBody User user){
//        userService.addUser(user);
//    }


}
