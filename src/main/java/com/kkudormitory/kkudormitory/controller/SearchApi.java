package com.kkudormitory.kkudormitory.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.*;
import com.kkudormitory.kkudormitory.model.repository.SearchRepository;


@CrossOrigin
@RestController
@RequestMapping("api/search")
public class SearchApi {

    @Autowired
	private SearchRepository repo;


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
            String[] imageUrls = imageUrlsStr.split(",");
            dormData.put("image_urls", Arrays.asList(imageUrls));
            String farStr = (String) ((Object[]) result)[5];
            String[] far = farStr.split(",");
            dormData.put("far", Arrays.asList(far));
            dormitories.add(dormData);
        }
        return dormitories;
    }
    

    
    @GetMapping("")
    public List<Map<String, Object>> getDormitory(@RequestParam("dorm") String search) throws SQLException, ClassNotFoundException {
        String newSearch = "%" + search + "%";
        List<Object> result = repo.searchDorm(newSearch);
        return mappingDorm(result);
    }
    

//     @GetMapping("/test/{search}")
//     public List<Object> getTest(@PathVariable("search") String search) throws SQLException, ClassNotFoundException {
//         String newSearch = "%" + search + "%";
//         return repo.searchDorm(newSearch);
// }

    

    // @GetMapping("/{search}")
    // public String getDormitory(@PathVariable("search") String search) throws SQLException, ClassNotFoundException {
    //     SearchRepo se = new SearchRepo();
    //     String newSearch = "%"+search+"%";
    //     return newSearch;
    // }
}
