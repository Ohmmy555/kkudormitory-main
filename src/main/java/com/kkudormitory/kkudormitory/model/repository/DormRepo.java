package com.kkudormitory.kkudormitory.model.repository;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kkudormitory.kkudormitory.model.bean.Dormitory;

public interface DormRepo extends JpaRepository<Dormitory, Integer>  {
    //	@Query(
    //			  value = "SELECT dormitory.dormID, dormitory.dorm_name, dormitory.address, zone.zonename "
    //			  		+ "FROM dormitory "
    //			  		+ "JOIN zone ON zone.zoneid = dormitory.zoneid "
    //			  		+ "ORDER BY dormitory.dormID ASC", 
    //			  nativeQuery = true)
    //	       Page<AdminMainObject> adminMain(Pageable pageable);
    //	
    
    //	    @Query(value = "SELECT dormitory.dormID, dormitory.dorm_name, dormitory.address, zone.zonename "
    //	                 + "FROM dormitory JOIN zone ON (zone.zoneid = dormitory.zoneid) "
    //	                 + "ORDER BY dormitory.dormID ASC",
    //	           nativeQuery = true)
        // @Query(value = "SELECT d.dormid,d.dorm_name,d.address,d.month_price,z.zonenamethai FROM dormitory d JOIN zone z ON d.zoneid = z.zoneid",
        //         nativeQuery = true)
        //     Object adminMain();

            @Query(value = "SELECT d.dormID, d.dorm_name, d.address, z.zonenamethai, d.month_price FROM dormitory d JOIN zone z ON z.zoneid = d.zoneid ORDER BY d.dormID DESC",
            nativeQuery = true)
			Page<List<Map<String,Object>>> adminMain(PageRequest pageable);
        
        
        @Query(value = "SELECT dormID,dorm_name, detail,month_price FROM dormitory  \n"
                + "WHERE zoneid=1",
                nativeQuery = true)
            List<Object> mainScreenFindMueang();

            @Query(value = "SELECT d.dormID, d.dorm_name, d.address, d.month_price , GROUP_CONCAT(i.image_name) AS image_urls, z.zonenameeng , z.zonenamethai, z.zoneid FROM dormitory d LEFT JOIN images i ON d.dormID = i.dormID LEFT JOIN zone z ON d.zoneid = z.zoneid WHERE d.zoneid=:zid GROUP BY d.dormID LIMIT 3",nativeQuery = true)
            List<Object> getMain(@Param("zid") Integer zid);
}
