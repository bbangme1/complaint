package com.complaint.service;

import com.complaint.DTO.ComplainWriteDTO;
import com.complaint.Entity.Complain;
import com.complaint.Entity.User;
import com.complaint.Repository.ComplainRepo;
import com.complaint.Repository.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ComplainService {
    @Autowired
    private ComplainRepo complainRepo;

    @Autowired
    private UserRepo userRepo;

    public void save(String name, ComplainWriteDTO writeDTO, List<MultipartFile> multipartFiles) {
        // 작성자의 id를 가지고 오기 위해 계정명으로 id 컬럼값 가져오기
        User user = userRepo.findById(name);

        // complain 클래스 객체 만들어서 데이터 넣어주기
        // 그래야 complain 테이블에 데이터 저장할 꺼니까
        Complain complain = new Complain();
        complain.setUserId(user.getId());
        complain.setCategory(writeDTO.getCategory());
        complain.setContent(writeDTO.getContent());
        complain.setTitle(writeDTO.getTitle());
    }
}
