package com.ums.controller;

import com.ums.dto.logindto;
import com.ums.dto.userdto;
import com.ums.service.userservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class authcontroller {


    private userservice userService;

    public authcontroller(userservice userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login( @RequestBody  logindto login) {

        String token = userService.verifyLogin(login);

        if (token!=null){
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("invalid credentials", HttpStatus.BAD_REQUEST);
        }





    }
    @PostMapping("/{adduser}")
    public ResponseEntity<userdto> adduser(@RequestBody userdto user){
        userdto userdto = userService.saveUser(user);
       return new ResponseEntity<userdto>(userdto, HttpStatus.CREATED);
    }







}
