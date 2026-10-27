package com.example.blog_app.security;

import com.example.blog_app.exception.ResourceNotFoundException;
import com.example.blog_app.models.User;
import com.example.blog_app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//      loading user from database
      User user =  this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","email"+username,0));
        return user;
    }
}
