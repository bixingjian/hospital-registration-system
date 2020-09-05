package com.demo.registration.service;

import com.demo.registration.dao.DoctorDao;
import com.demo.registration.dao.RegistrationDao;
import com.demo.registration.entity.Department;
import com.demo.registration.entity.Doctor;
import com.demo.registration.entity.Registration;
import com.demo.registration.entity.User;
//import javafx.scene.control.DateCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RegistrationService {
    @Autowired
    RegistrationDao registrationDao;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    UserService userService;
    @Autowired
    DoctorService doctorService;
    public String addNew( Registration registration)
    {

        Department department = departmentService.getDepartmentByName(registration.getDepartmentName());
        if(department==null)
        {
            return "error1";
        }
        Doctor doctor = doctorService.findInfoByName(registration.getDoctorName(),department.getId());
        if(doctor==null)
        {
            return "error2";
        }
        try {
            int age = getAgeByBirth(registration.getBirthday());
            registration.setAge(age);
        } catch (ParseException e) {
            return "error4";
        }

        registration.setDepartmentId(department.getId());
        registration.setDoctorId(doctor.getId());
        if(doctor.getNum()!=-1)
        {
            int num = registrationDao.countNumByDoctorId(registration);
            if(num>=doctor.getNum())
            {
                return "error3";
            }
        }

        registrationDao.addNew(registration);
        return "success";
    }
    public String delete(Registration registration)
    {
        registrationDao.delete(registration);
        return "success";
    }
    public List<Registration> findInfo(Registration registration)
    {
        List<Registration> list =registrationDao.findInfo(registration);
        return list;
    }
    public int countInfo(Registration registration)
    {
        int num = registrationDao.countInfo(registration);
        return num;
    }
    public String update(Registration registration)
    {
        Department department = departmentService.getDepartmentByName(registration.getDepartmentName());
        if(department==null)
        {
            return "error1";
        }
        Doctor doctor = doctorService.findInfoByName(registration.getDoctorName(),department.getId());
        if(doctor==null)
        {
            return "error2";
        }

        registration.setDepartmentId(department.getId());
        registration.setDoctorId(doctor.getId());

        try {
            int age = getAgeByBirth(registration.getBirthday());
            registration.setAge(age);
        } catch (ParseException e) {
            return "error4";
        }
        if(doctor.getNum()!=-1)
        {
            int num = registrationDao.countNumByDoctorId(registration);
            if(num>=doctor.getNum())
            {
                return "error3";
            }
        }


        registrationDao.update(registration);
        return "success";
    }
    public  int getAgeByBirth(String strDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDay = sdf.parse(strDate);

        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }
    public List<Registration> findInfoByDoctor(Registration registration) {
       User user =  userService.getUser();
       int doctorId= user.getDoctorId();
        registration.setDoctorId(doctorId);
        List<Registration> list =registrationDao.findInfoByDoctor(registration);
        return list;
    }
    public int countInfoByDoctor(Registration registration) {
        User user =  userService.getUser();
        int doctorId= user.getDoctorId();
        registration.setDoctorId(doctorId);
        int num = registrationDao.countInfoByDoctor(registration);
        return num;
    }
    public String updateFinsh(Registration registration) {
        registrationDao.updateFinsh(registration);
        return "success";
    }
}
