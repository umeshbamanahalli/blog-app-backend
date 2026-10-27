package com.example.blog_app.controllers;

import com.example.blog_app.exception.ApiException;
import com.example.blog_app.models.User;
import com.example.blog_app.payloads.JwtAuthRequest;
import com.example.blog_app.payloads.UserDto;
import com.example.blog_app.repository.UserRepo;
import com.example.blog_app.security.JwtAuthResponse;
import com.example.blog_app.security.JwtTokenHelper;
import com.example.blog_app.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo  userRepo;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@Valid @RequestBody JwtAuthRequest request) throws Exception {
        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

        String token = this.jwtTokenHelper.generateToken(userDetails);

        User user = this.userRepo.findByEmail(request.getUsername()) // or findByUsername depending on your setup
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        response.setUser(userDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        try {
            this.authenticationManager.authenticate(token);
        } catch (BadCredentialsException e) {

            throw new ApiException("Bad credentials");
        }
    }

    //    register new user api
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) throws Exception {
        UserDto registeredUser = this.userService.registerNewUser(userDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

}
