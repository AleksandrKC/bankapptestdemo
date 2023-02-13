package com.example.bank.controllers;

import com.example.bank.entities.User;
import com.example.bank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class BankController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }
    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(Principal principal){
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(principal.getName());
        return "pageForAuthenticatedUsers " + user.getUsername() + " " + user.getEmail();
    }
    @GetMapping("/read_profile")
    public String pageForReadProfile(){
        return "read profile page";
    }
    @GetMapping("/only_for_admins")
    public String pageOnlyForAdmins(){
        return "admins page";
    }
//    private UserService userService;

//    @Autowired
//    public void setUserService(UserService userService){
//        this.userService = userService;
//    }
//    @GetMapping("/")
//    public String homePage(){
//        return "here will be home page";
//    }
//
//    @GetMapping("/user")
//    public String userPage(){
//        return "here will be home page";
//    }

//    @GetMapping("/authenticateduser")
//    public String pageForAuthenticatedUser(Principal principal){
//        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findByUsername(principal.getName());
//        return "here will be page for user " + user.getUsername();
//    }
//    @GetMapping("/authenticatedmanager")
//    public String pageForAuthenticatedManager(Principal principal){
//        User user = userService.findByUsername(principal.getName());
//        return "here will be page for manager";

}
