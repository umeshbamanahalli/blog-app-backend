package com.example.blog_app.services;

import com.example.blog_app.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerNewUser(UserDto userDto);
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer id);
    UserDto getUserById(Integer id);
    List<UserDto> getAllUsers();
    void deleteUser(Integer id);

    UserDto getUserByUsername(String username);
}
