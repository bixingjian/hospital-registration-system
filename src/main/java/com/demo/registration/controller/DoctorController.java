package com.demo.registration.controller;

import com.demo.registration.entity.Department;
import com.demo.registration.entity.Doctor;
import com.demo.registration.service.DepartmentService;
import com.demo.registration.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @RequestMapping("/addNew")
    public String addNew(@RequestBody Doctor doctor)
    {
        return doctorService.addNew(doctor);
    }

    @RequestMapping("/delete")
    public String delete(@RequestBody Doctor doctor)
    {
        return doctorService.delete(doctor);
    }

    @RequestMapping("/findInfo")
    public List<Doctor> findInfo(@RequestBody Doctor doctor)
    {
        return doctorService.findInfo(doctor);
    }

    @RequestMapping("/countInfo")
    public int countInfo(@RequestBody Doctor doctor)
    {
        return doctorService.countInfo(doctor);
    }

    @RequestMapping("/update")
    public String update(@RequestBody Doctor doctor)
    {
        return doctorService.update(doctor);
    }



}
