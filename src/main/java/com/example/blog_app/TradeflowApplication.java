package com.example.blog_app;

import com.example.blog_app.config.AppConstants;
import com.example.blog_app.models.Role;
import com.example.blog_app.repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TradeflowApplication implements CommandLineRunner {
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public void run(String... args) throws Exception {
        try {
            Role role = new Role();
            role.setId(AppConstants.ADMIN_USER);
            role.setName("ROLE_ADMIN");

            Role role2 = new Role();
            role2.setId(AppConstants.NORMAL_USER);
            role2.setName("ROLE_NORMAL");

            List<Role> roles = List.of(role, role2);

            List<Role> result = roleRepo.saveAll(roles);

            result.forEach(r -> {
                System.out.println(r.getName());
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(TradeflowApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
