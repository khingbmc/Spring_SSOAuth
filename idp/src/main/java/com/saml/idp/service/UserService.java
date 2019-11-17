package com.saml.idp.service;


import com.saml.idp.model.User;


public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepositories userRepositories;
//
//    public List<User> getAllUsers(){
//        return userRepositories.findAll();
//    }
//
//    public void addUser(User user){
//        userRepositories.save(user);
//    }
//
//    public void deleteUser(Long id) {
//        userRepositories.deleteById(id);
//    }
//}
