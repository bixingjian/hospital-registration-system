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
@RequestMapping("/doctorDo")
public class DoctorDoController {
    @Autowired
    RegistrationService registrationService;

    @RequestMapping("/findInfo")
    public List<Registration> findInfo(@RequestBody Registration registration)
    {
        return registrationService.findInfoByDoctor(registration);
    }

    @RequestMapping("/countInfo")
    public int countInfo(@RequestBody Registration registration)
    {
        return registrationService.countInfoByDoctor(registration);
    }
    @RequestMapping("/updateFinsh")
    public String updateFinsh(@RequestBody Registration registration)
    {
        return registrationService.updateFinsh(registration);
    }


}
