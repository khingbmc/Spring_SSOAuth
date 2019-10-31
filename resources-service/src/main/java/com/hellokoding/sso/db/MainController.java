//package com.hellokoding.sso.db;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping(path="/db")
//public class MainController {
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping(path = "/add")
//    public @ResponseBody String addNewUser(
//            @RequestParam String username
//            , @RequestParam String password
//            , @RequestParam String group
//            , @RequestParam String email){
//
//        User n = new User();
//        n.setUsername(username);
//        n.setPassword(password);
//        n.setEmail(email);
//        n.setGroup(group);
//        return "Saved";
//    }
//
//    @GetMapping(path = "/all")
//    public @ResponseBody Iterable<User> getAllUser(){
//        return userRepository.findAll();
//    }
//}
