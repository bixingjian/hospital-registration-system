package com.demo.registration.dao;

import com.demo.registration.entity.Registration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface RegistrationDao {
    public int addNew(@Param("registration") Registration registration);

    public int delete(@Param("registration") Registration registration);

    public List<Registration> findInfo(@Param("registration") Registration registration);

    public int countInfo(@Param("registration") Registration registration);

    public int update(@Param("registration") Registration registration);

    public Registration findInfoByName(@Param("registration") Registration registration);

    int countNumByDoctorId(@Param("registration") Registration registration);

    List<Registration> findInfoByDoctor(@Param("registration")Registration registration);
    public int countInfoByDoctor(@Param("registration") Registration registration);

    int updateFinsh(@Param("registration") Registration registration);
}
