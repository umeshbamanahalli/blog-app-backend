package com.example.blog_app.services.impl;

import com.example.blog_app.config.AppConstants;
import com.example.blog_app.exception.ResourceNotFoundException;
import com.example.blog_app.models.Role;
import com.example.blog_app.models.User;
import com.example.blog_app.payloads.UserDto;
import com.example.blog_app.repository.RoleRepo;
import com.example.blog_app.repository.UserRepo;
import com.example.blog_app.services.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;


    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = this.roleRepo.findById(502) // Replace 252 with your actual normal role ID or fetch by name "ROLE_NORMAL"
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", 502));

        user.getRoles().add(role);
        User newUser = userRepo.save(user);
        return modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    @Transactional // 👈 Critical for saving updates to the database
    public UserDto updateUser(UserDto userDto, Integer id) {
        User user = this.userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));

        // Only update name and about from the profile page
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());

        // Do NOT overwrite password/email here so they don't get wiped out or trigger validation

        User updatedUser = this.userRepo.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        this.userRepo.delete(user);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = this.userRepo.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "Email"+username,0));
        return this.userToDto(user);
    }


    public User dtoToUser(UserDto userDto) {
        //        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return this.modelMapper.map(userDto, User.class);
    }

    public UserDto userToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
