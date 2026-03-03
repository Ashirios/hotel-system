package com.hotel.hotel_system.api.controllers;



import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.hotel.hotel_system.api.dto.requests.LoginRequestDto;
import com.hotel.hotel_system.api.dto.requests.RegistrationDto;
import com.hotel.hotel_system.api.dto.requests.UserUpdateDto;
import com.hotel.hotel_system.api.dto.responses.UserProfileDto;
import com.hotel.hotel_system.api.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {


    
    private UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping("/register")
    public UserProfileDto addUser(@RequestBody RegistrationDto dto){
       return service.addUser(dto);
    }

    
    @PostMapping("/{id}")
    public UserProfileDto update(@RequestBody UserUpdateDto dto, @PathVariable Long id){
        return service.updateUser(dto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteUser(id);
    }

    @PostMapping("/login")
    public UserProfileDto login(@RequestBody LoginRequestDto dto){
        return service.login(dto);
    }

    @GetMapping("/{id}")
    public UserProfileDto getUser(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping
    public List<UserProfileDto> getAllUsers() {
        return service.getAllUsers();
    }


    



}
