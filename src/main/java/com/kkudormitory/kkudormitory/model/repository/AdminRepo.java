package com.kkudormitory.kkudormitory.model.repository;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kkudormitory.kkudormitory.model.bean.Admin;

public interface AdminRepo extends CrudRepository<Admin, Integer> {
    @Query(value = "SELECT userid,username FROM `admin` WHERE `username`=:username AND `userpassword` = :password" ,nativeQuery = true)
    Map<String,Object> login(@Param("username") String username,@Param("password") String password);
    
}
