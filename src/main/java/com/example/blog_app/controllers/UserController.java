package com.example.blog_app.controllers;


import com.example.blog_app.payloads.ApiResponse;
import com.example.blog_app.payloads.UserDto;
import com.example.blog_app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto userDto) {
        UserDto createUserDto = this.userService.createUser(userDto);
        return ResponseEntity.ok(createUserDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
        this.userService.updateUser(userDto, id);
        return ResponseEntity.ok(userDto);
    }


//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteUser(@PathVariable Integer id) {
        this.userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

    // Endpoint to get the logged-in user profile using the token
    @GetMapping("/current-user")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal) {
        // principal.getName() usually returns the username/email extracted from the JWT token
        String username = principal.getName();
        UserDto userDto = this.userService.getUserByUsername(username);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
