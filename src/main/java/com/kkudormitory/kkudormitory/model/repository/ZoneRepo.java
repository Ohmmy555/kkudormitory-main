package com.kkudormitory.kkudormitory.model.repository;
import org.hibernate.mapping.Map;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.kkudormitory.kkudormitory.model.bean.*;

public interface ZoneRepo extends JpaRepository<Zone, Integer> {
    
    // @Query(value = "SELECT d.dormID, d.dorm_name, d.address, z.zonename FROM dormitory d JOIN zone z ON z.zoneid = d.zoneid ORDER BY d.dormID DESC",
    //             nativeQuery = true)
    //         List<Object> adminMain();

    @Query(value = "SELECT COUNT(*) FROM zone", nativeQuery = true)
    Integer getCount();

    @Query(value = "SELECT d.dormID, d.dorm_name, d.address, d.month_price, GROUP_CONCAT(i.image_name) AS image_urls,GROUP_CONCAT(facilites.fac_name) AS facil, z.zonenamethai FROM dormitory d LEFT JOIN images i ON d.dormID = i.dormID LEFT JOIN facilites ON (facilites.dormid = d.dormid) LEFT JOIN zone z ON d.zoneid = z.zoneid WHERE d.zoneid =:zid GROUP BY d.dormID",
    nativeQuery = true)
    List<Object> getZone(Integer zid);


    // @Query(value = "SELECT d.dormID, d.dorm_name, d.address, d.month_price, GROUP_CONCAT(i.image_name) AS image_urls,GROUP_CONCAT(facilites.fac_name) AS facil, z.zonenamethai FROM dormitory d LEFT JOIN images i ON d.dormID = i.dormID LEFT JOIN facilites ON facilites.dormid = d.dormid LEFT JOIN zone z ON d.zoneid = z.zoneid WHERE d.zoneid =:zid GROUP BY d.dormID LIMIT :pageSize OFFSET :offset",
    // nativeQuery = true)
    // List<Object> getZone(@Param("zid") Integer zone, @Param("pageSize") int pageSize, @Param("offset") int offset);

    // @Query(value = "SELECT COUNT(*) FROM dormitory WHERE zoneid = :zoneid", nativeQuery = true)
    // long countByZone(@Param("zoneid") Integer zoneid);

    // @Query(value = "SELECT d.dormID, d.dorm_name, d.address, d.month_price, GROUP_CONCAT(i.image_name) AS image_urls,GROUP_CONCAT(facilites.fac_name) AS facil, z.zonenamethai FROM dormitory d LEFT JOIN images i ON d.dormID = i.dormID LEFT JOIN facilites ON (facilites.dormid = d.dormid) LEFT JOIN zone z ON d.zoneid = z.zoneid WHERE d.zoneid =:zid GROUP BY d.dormID LIMIT :limit OFFSET :offset",
    // nativeQuery = true)
    // List<Object> getZone(Integer zid, int limit, int offset);

}
