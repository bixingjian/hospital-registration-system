package com.demo.registration.controller;

import com.demo.registration.entity.Department;
import com.demo.registration.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @RequestMapping("/addNew")
    public String addNew(@RequestBody Department department)
    {
        return departmentService.addNew(department);
    }

    @RequestMapping("/delete")
    public String delete(@RequestBody Department department)
    {
        return departmentService.delete(department);
    }

    @RequestMapping("/findInfo")
    public List<Department> findInfo(@RequestBody Department department)
    {
        return departmentService.findInfo(department);
    }

    @RequestMapping("/countInfo")
    public int countInfo(@RequestBody Department department)
    {
        return departmentService.countInfo(department);
    }

    @RequestMapping("/update")
    public String update(@RequestBody Department department)
    {
        return departmentService.update(department);
    }



}
