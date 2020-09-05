package com.demo.registration.controller;

import com.demo.registration.entity.Doctor;
import com.demo.registration.entity.Registration;
import com.demo.registration.service.DoctorService;
import com.demo.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;
    @RequestMapping("/addNew")
    public String addNew(@RequestBody Registration registration)
    {
        return registrationService.addNew(registration);
    }

    @RequestMapping("/delete")
    public String delete(@RequestBody Registration registration)
    {
        return registrationService.delete(registration);
    }

    @RequestMapping("/findInfo")
    public List<Registration> findInfo(@RequestBody Registration registration)
    {
        return registrationService.findInfo(registration);
    }

    @RequestMapping("/countInfo")
    public int countInfo(@RequestBody Registration registration)
    {
        return registrationService.countInfo(registration);
    }

    @RequestMapping("/update")
    public String update(@RequestBody Registration registration)
    {
        return registrationService.update(registration);
    }



}
