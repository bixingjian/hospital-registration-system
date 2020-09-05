package com.demo.registration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }
    @RequestMapping("/department")
    public String department()
    {
        return "department";
    }
    @RequestMapping("/doctor")
    public String doctor()
    {
        return "doctor";
    }
    @RequestMapping("/registration")
    public String registration()
    {
        return "registration";
    }

    @RequestMapping("/doctorDo")
    public String doctorDo()
    {
        return "doctorDo";
    }
}
