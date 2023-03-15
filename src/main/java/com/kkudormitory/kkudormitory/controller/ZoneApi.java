package com.kkudormitory.kkudormitory.controller;

import java.sql.*;
import java.util.*;
import java.lang.Object;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.*;

import com.kkudormitory.kkudormitory.model.repository.*;
import com.kkudormitory.kkudormitory.model.bean.*;

@CrossOrigin
@RestController
@RequestMapping("api/zone")
public class ZoneApi {

    @Autowired
	private ZoneRepo repo2;
    @Autowired
	private DormRepo repo;

    // @GetMapping("/{id}")
    // public List<Map<String, Object>> getDormitory(@PathVariable("id") Integer id) throws SQLException, ClassNotFoundException {
    //     DormDAO dorm = new DormDAO();
    //     return dorm.getDormitory(id);

    // }

    @GetMapping("/all")
    public List<Map<String, Object>> allDormitory() throws SQLException, ClassNotFoundException{
        DormDAO dorm = new DormDAO();
        return dorm.allDormitory();
    }

//     @GetMapping("/{id}")
// public Page<Map<String, Object>> getDormitory(@PathVariable("id") Integer id, Pageable pageable) throws SQLException, ClassNotFoundException {
//     DormDAO dorm = new DormDAO();
//     return repo2.getMain(id, pageable);

// private List<Map<String, Object>> mappingDorm(List<Object> resultList){
//     List<Map<String, Object>> dormitories = new ArrayList<>();
//     for (Object result : resultList) {
//         Map<String, Object> dormData = new HashMap<>();
//         dormData.put("aname", ((Object[]) result)[1]);
//         dormData.put("ID", ((Object[]) result)[0]);
//         dormData.put("detail", ((Object[]) result)[2]);
//         dormData.put("price", ((Object[]) result)[3]);
//         dormData.put("zone", ((Object[]) result)[6]);
//         String imageUrlsStr = (String) ((Object[]) result)[4];
//         String[] imageUrls = imageUrlsStr.split(",");
//         dormData.put("image_urls", Arrays.asList(imageUrls));
//         String farStr = (String) ((Object[]) result)[5];
//         String[] far = farStr.split(",");
//         dormData.put("far", Arrays.asList(far));
//         dormitories.add(dormData);
//     }
//     return dormitories;
// }

// @GetMapping("/{zone}")
// public List<Map<String, Object>> getDormitory(@PathVariable("zone") Integer zone) throws SQLException, ClassNotFoundException {
//     List<Object> result = repo2.getZone(zone);
//     return mappingDorm(result);
// }

private List<Map<String, Object>> mappingDorm(List<Object> resultList){
    List<Map<String, Object>> dormitories = new ArrayList<>();
    for (Object result : resultList) {
        Map<String, Object> dormData = new HashMap<>();
        dormData.put("aname", ((Object[]) result)[1]);
        dormData.put("ID", ((Object[]) result)[0]);
        dormData.put("detail", ((Object[]) result)[2]);
        dormData.put("price", ((Object[]) result)[3]);
        dormData.put("zone", ((Object[]) result)[6]);
        String imageUrlsStr = (String) ((Object[]) result)[4];
        if(imageUrlsStr != null){
            String[] imageUrls = imageUrlsStr.split(",");
            dormData.put("image_urls", Arrays.asList(imageUrls));
        }
        String farStr = (String) ((Object[]) result)[5];
        if(farStr != null){
            String[] far = farStr.split(",");
            dormData.put("far", Arrays.asList(far));
        }
        dormitories.add(dormData);
    }
    return dormitories;
}

@GetMapping("/{zone}")
public List<Map<String, Object>> getDormitory(@PathVariable("zone") Integer zone) throws SQLException, ClassNotFoundException {
    List<Object> result = repo2.getZone(zone);
    return mappingDorm(result);
}

// @GetMapping("/{zone}")
// public List<Map<String, Object>> getDormitory(@PathVariable("zone") Integer zone, Pageable pageable) throws SQLException, ClassNotFoundException {
//     int pageSize = pageable.getPageSize();
//     int offset = (int) pageable.getOffset();
//     List<Object> result = repo2.getZone(zone, pageSize, offset);
//     return mappingDorm(result);
// }







}




