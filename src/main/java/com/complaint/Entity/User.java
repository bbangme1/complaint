package com.complaint.Entity;

import com.complaint.DTO.UserDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
public class User {
    private long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String userId;
    private LocalDateTime createdAt;
    private String tel;
    //dto -> entity
    public static User from(UserDTO userDTO, PasswordEncoder passwordEncoder){
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setTel(userDTO.getTel());
        user.setRole("USER");


        return user;
    }
}

