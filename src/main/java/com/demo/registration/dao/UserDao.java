package com.demo.registration.dao;

import com.demo.registration.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    public String findPasswordByUsername(@Param("username")String username);

    User findUserByUsername(@Param("username")String userName);
}
