package com.kkudormitory.kkudormitory.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.kkudormitory.kkudormitory.model.bean.Dormitory;

public interface SearchRepository extends JpaRepository<Dormitory, Integer> {
    @Query(value = "SELECT d.dormID, d.dorm_name, d.address, d.month_price, GROUP_CONCAT(i.image_name) AS image_urls,GROUP_CONCAT(facilites.fac_name) AS facil, z.zonenamethai FROM dormitory d LEFT JOIN images i ON d.dormID = i.dormID JOIN facilites ON (facilites.dormid = d.dormid) LEFT JOIN zone z ON d.zoneid = z.zoneid WHERE d.dorm_name LIKE :search GROUP BY d.dormID",
    nativeQuery = true)
    List<Object> searchDorm(@Param("search") String search);
}
