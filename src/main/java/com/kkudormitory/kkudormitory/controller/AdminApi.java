package com.kkudormitory.kkudormitory.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kkudormitory.kkudormitory.model.bean.Admin;
import com.kkudormitory.kkudormitory.model.repository.AdminRepo;
import com.kkudormitory.kkudormitory.model.repository.DormRepo;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import java.util.List;
import java.util.Map;



@CrossOrigin
@RestController
@RequestMapping("api/admin")
public class AdminApi {
    @Autowired
    private DormRepo repo; 
    @Autowired
    private AdminRepo repo2; 

    // @GetMapping("")
    // public Object testAdmin(){
    //     return repo.adminMain();
    // }
    @GetMapping("")
	public Page<List<Map<String,Object>>>  aa(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy) {
		PageRequest pageable = PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy.orElse("dormID"));
		
		return repo.adminMain(pageable);
	}

    // @PostMapping("/login")
    // public String loginAdmin(@ModelAttribute Admin admin, Model model){
    //     // String pass = admin.getPassword();
    //     // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //     // String hashedPassword = passwordEncoder.encode(pass);
    //     repo2.save(admin);
    //     return "Su";
    // }

    // @PostMapping("/login")
    // public List<Admin> loginAdmin(@ModelAttribute Admin admin){
    //     // List<Admin> userID = repo2.login(admin.getuserName(), admin.getPassword());
    //     System.out.println(admin.getPassword());
    //     return null;
    // }

    @PostMapping("/login")
    public Map<String,Object> loginAdmin(@RequestBody Admin admins) throws NoSuchAlgorithmException{
        String username = admins.getUsername();
        String password = admins.getUserpassword();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        System.out.println("Encoded is " + encoded);
        System.out.println(username+" "+encoded);
         return repo2.login(username, encoded);  
    }

    @PostMapping("/signup")
    public String signupAdmin(@RequestBody Admin admins) throws NoSuchAlgorithmException{
        String pass = admins.getUserpassword();
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        System.out.println("Encoded is " + encoded);
        admins.setUserpassword(encoded);
        repo2.save(admins);
        return "{message : Sign up successfully}";
    }
}
