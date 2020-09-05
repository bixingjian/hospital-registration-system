package com.demo.registration.service;

import com.demo.registration.dao.DepartmentDao;
import com.demo.registration.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentDao departmentDao;
    public String addNew(@RequestBody Department department)
    {
        departmentDao.addNew(department);
        return "success";
    }


    public String delete(@RequestBody Department department)
    {
        departmentDao.delete(department);
        return "success";
    }


    public List<Department> findInfo(@RequestBody Department department)
    {
        List<Department> list =departmentDao.findInfo(department);
        return list;
    }


    public int countInfo(@RequestBody Department department)
    {
        int num = departmentDao.countInfo(department);
        return num;
    }


    public String update(@RequestBody Department department)
    {
        departmentDao.update(department);
        return "success";
    }
    public Department getDepartmentByName(String departmentName)
    {
        Department department = new Department();
        department.setDepartmentName(departmentName);
        department = departmentDao.findInfoByName(department);
        return department;
    }
}
