package com.complaint.control;

import com.complaint.DTO.LoginDTO;
import com.complaint.DTO.UserDTO;
import com.complaint.Repository.UserRepo;
import com.complaint.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;



    // 로그인 실패시 제공 페이지
    @GetMapping("/signIn/error")
    public String loginFail(Model model){
        model.addAttribute("LoginError","아이디 또는 비밀번호가 잘못 되었습니다.");
        return "member/signIn";
    }


    // 로그인 페이지 요청
    @GetMapping("/signIn")
    public String signIn(Model model){
        model.addAttribute("loginDTO", new LoginDTO());
        return "member/signIn";
    }

    @GetMapping("/signUp")
    public String signUp(Model model){
        model.addAttribute("userDTO", new UserDTO());

        return "member/signUp";
    }
    //회원가입 작성 데이터 처리(가입-저장) 요청
    @PostMapping("/signUp")
    public String signUpProcess(@Valid UserDTO userDTO, BindingResult bindingResult , Model model){
       // 유효성 검사 실패 : 검사 통과 못함
        if(bindingResult.hasErrors()){
            return "member/signUp";      // 유효하지 않은 값이 있으니 다시 회원가입 페이지로 돌려보내기
        }

        // 회원가입 데이터 저장 실행
        try {
            userService.save(userDTO, passwordEncoder);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMsg", e.getMessage());
               return "member/signUp";
        }
        return "redirect:/";
    }
}
