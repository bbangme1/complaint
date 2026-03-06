package com.complaint.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

     @GetMapping("/")
     public String index(){

         return "index";
     }
}

   // 회원가입 , 로그인 , 민원글 작성 , 민원글 목록 , 민원글 수정 , 삭제
   // 민원글 답변 , 답변에 질문 작성하고 또 답변 달고 하는 구조로
   // 지금까지 수업한거 spring boot 하면서
   // control , repository , DTO , view(html) ,
   // thymeleaf , mybatis , maven
   // 지금 프로젝트에서 할거
   // gradle , validation , security ,service , layout
   // 1. latout  2. val 3. service 4. security
   //