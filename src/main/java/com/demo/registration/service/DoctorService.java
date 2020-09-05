package com.demo.registration.service;

import com.demo.registration.dao.DepartmentDao;
import com.demo.registration.dao.DoctorDao;
import com.demo.registration.entity.Department;
import com.demo.registration.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    DoctorDao doctorDao;
    @Autowired
    DepartmentService departmentService;
    public String addNew( Doctor doctor)
    {

        Department department = departmentService.getDepartmentByName(doctor.getDepartmentName());
        if(department==null)
        {
            return "error";
        }
        doctor.setDepartmentId(department.getId());
        doctorDao.addNew(doctor);
        return "success";
    }


    public String delete( Doctor doctor)
    {
        doctorDao.delete(doctor);
        return "success";
    }


    public List<Doctor> findInfo( Doctor doctor)
    {
        List<Doctor> list =doctorDao.findInfo(doctor);
        return list;
    }


    public int countInfo( Doctor doctor)
    {
        int num = doctorDao.countInfo(doctor);
        return num;
    }


    public String update( Doctor doctor)
    {
        Department department = departmentService.getDepartmentByName(doctor.getDepartmentName());
        if(department==null)
        {
            return "error";
        }

        doctorDao.update(doctor);
        return "success";
    }

    public Doctor findInfoByName(String doctorName,int departmenId)
    {
        Doctor doctor = new Doctor();
        doctor.setDoctorName(doctorName);
        doctor.setDepartmentId(departmenId);
        doctor = doctorDao.findInfoByName(doctor);
        return doctor;
    }
}
