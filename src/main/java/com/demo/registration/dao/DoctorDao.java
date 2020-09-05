package com.demo.registration.dao;

import com.demo.registration.entity.Department;
import com.demo.registration.entity.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DoctorDao {

    public int addNew(@Param("doctor") Doctor doctor);


    public int delete(@Param("doctor") Doctor doctor);

    public List<Doctor> findInfo(@Param("doctor") Doctor doctor);

    public int countInfo(@Param("doctor") Doctor doctor);

    public int update(@Param("doctor") Doctor doctor);

    public Doctor findInfoByName(@Param("doctor") Doctor doctor);
}
