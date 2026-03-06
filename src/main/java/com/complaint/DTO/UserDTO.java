package com.complaint.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {   //회원가입 페이지용 클래스
    @NotBlank( message = " 아이디는 필수 입니다. " )
    @Size( min=3, max=12 , message = "아이디는 3자 이상 12자 이하 입니다." )
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입니다." )
    @Size( min = 4 , max = 20 , message = "비밀번호는 4자이상 20자 이하입니다." )
    @Pattern( regexp = "^[a-z0-9]+$" , message = "비밀번호는 소문자+숫자 여야 합니다." )
    private String password;

    @Email( message = "이메일 형식이 아닙니다." )
    private String email;

    @NotBlank( message = "이름은 필수 입력입니다." )
    private String name;

    @Pattern( regexp = "^\\d{11}$" , message = "연락처는 숫자 11자리여야 합니다." )
    private String tel;


}


   // @NotNull => null 이면 안된다. 허용값 : "", " " , "문자열 아무거나"
   // @NotEmpty => null 또는 길이가 0이면 안된다.  허용값 : " ","문자열 아무거나"
   // @NotBlank => null,"", 공백만 있는 문자열 모두 금지