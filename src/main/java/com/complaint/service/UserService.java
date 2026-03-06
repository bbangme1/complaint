package com.complaint.service;

import com.complaint.DTO.UserDTO;
import com.complaint.Entity.User;
import com.complaint.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

     @Autowired
     private UserRepo userRepo;

    public void save( UserDTO userDTO, PasswordEncoder passwordEncoder) {
           // 회원가입 입력한 데이터 객체인 DTO를 Entity 객체로 옮기기
           // Entity 클래스에서 비밀번호를 암호화 시키기
           // Repository를 통해 Entity객체의 값 테이블에 저장
         User user = User.from(userDTO, passwordEncoder);

         validId(user); // 테이블에 저장하기 전에 아이디 중복 체크
         userRepo.save(user);

    }

    // 아이디 중복체크 하는 메서드
    private void validId(User user){
        User find = userRepo.findById(user.getUserId());
        if( find != null){
            throw new IllegalStateException("아이디가 중복입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {

        User user = userRepo.findById(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }

        return org.springframework.security.core.userdetails.User
                .builder().username(user.getUserId())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }



}
