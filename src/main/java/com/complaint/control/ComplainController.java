package com.complaint.control;

import com.complaint.DTO.ComplainWriteDTO;
import com.complaint.service.ComplainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
public class ComplainController {
    @Autowired
    private ComplainService complainService;

    //민원 상세 페이지 요청
    @GetMapping("/detail")
    public String detail(@RequestParam("id") long id, Model model){
        model.addAttribute("detail", complainService.getDetail(id));
        return "board/detail";
    }

    // 민원 조회 - 로그인 계정별 작성 글만 조회
    @GetMapping("/list")
    public String listComplain(Model model, Principal principal){
        String userName = principal.getName(); // 로그인 계정명
        model.addAttribute("list",complainService.getList(userName));
        return "board/list";
    }

    // 민원 작성 데이터를 처리 - 이미지도 같이 처리
    @PostMapping("/writeSave")
    public String writeSave(@Valid ComplainWriteDTO complainWriteDTO, BindingResult bindingResult ,
                            @RequestParam("imgFile")List<MultipartFile> multipartFiles, Principal principal, Model model){
        if(bindingResult.hasErrors()){
            return "board/write";
        }
        // 잘 입력 했다면 테이블에 저장
        try{
            complainService.save(principal.getName() ,complainWriteDTO ,multipartFiles);
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("message","이미지 또는 파일 업로드 실패");
            return "board/write";
        }
        return "redirect:/";
    }

    // 민원 작성 페이지 요청 - 민원신청 클릭하면
    @GetMapping("/write")
    public String write(Model model){
        model.addAttribute("complainWriteDTO",new ComplainWriteDTO());
        return "board/write";
    }

}
