package com.demo.registration.dao;

import com.demo.registration.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface DepartmentDao {

    public int addNew(@Param("department") Department department);


    public int delete(@Param("department")Department department);

    public List<Department> findInfo(@Param("department")Department department);

    public int countInfo(@Param("department")Department department);

    public int update(@Param("department")Department department);

    public Department findInfoByName(@Param("department")Department department);
}
