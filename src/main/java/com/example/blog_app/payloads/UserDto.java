package com.example.blog_app.payloads;



import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private int id;

    @Size(min = 4, message = "Username must be minimum of 4 characters!!")
//    @NotEmpty(message = "Username must be minimum of 4 characters!!")
    private String name;

    @Email(message = "Email address is not valid!!")
    @NotEmpty(message = "Email is required!!")
    private String email;


    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars")
    private String password;

    @NotEmpty(message = "About is required!!")
    private String about;

    private Set<RoleDto> roles = new HashSet<>();

}
