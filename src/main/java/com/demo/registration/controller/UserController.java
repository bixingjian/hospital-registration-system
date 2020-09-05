package com.demo.registration.controller;

import com.demo.registration.entity.User;
import com.demo.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/submitLogin")
    @ResponseBody
    public User submitLogin(@RequestBody User user){
        User result =  userService.submitLogin(user.getUsername(),user.getPassword());
        return  result;
    }
}
