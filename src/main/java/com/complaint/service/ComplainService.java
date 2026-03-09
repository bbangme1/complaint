package com.complaint.service;

import com.complaint.DTO.ComplainWriteDTO;
import com.complaint.DTO.DetailDTO;
import com.complaint.DTO.ImgDTO;
import com.complaint.DTO.ListDTO;
import com.complaint.Entity.Complain;
import com.complaint.Entity.ComplainFile;
import com.complaint.Entity.ComplainReply;
import com.complaint.Entity.User;
import com.complaint.Repository.ComplainRepo;
import com.complaint.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplainService {
    @Autowired
    private ComplainRepo complainRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ComplainImageService complainImageService;

    @Autowired
    private ReplyService replyService;

    public void save(String name, ComplainWriteDTO writeDTO, List<MultipartFile> multipartFiles) throws Exception {
        // 작성자의 id를 가지고 오기 위해 계정명으로 id 컬럼값 가져오기
        User user = userRepo.findById(name);

        // complain 클래스 객체 만들어서 데이터 넣어주기
        // 그래야 complain 테이블에 데이터 저장할 꺼니까
        Complain complain = new Complain();
        complain.setUserId(user.getId());
        complain.setCategory(writeDTO.getCategory());
        complain.setContent(writeDTO.getContent());
        complain.setTitle(writeDTO.getTitle());
        // 민원테이블에 저장하기
        complainRepo.save(complain);

        // 민원 테이블에 저장하고 저장된 id컬럼값 가져오기
        Complain data = complainRepo.find(complain.getUserId());

        // 이미지나 파일은 민원테이블의 id컬럼값이 필요하므로
        // 민원 테이블 저장한 이후에 한다.
        complainImageService.saveImg(multipartFiles,data.getId());
    }

    public List<ListDTO> getList(String userName) {
        // 로그인 계정명으로 계정 정보 가져오기 (id 컬럼이 필요하다)
        // id 컬럼으로 민원글에서 조회 해야 한다.
        // (민원테이블 작성자의 값이 계정 id로 저장되어있다.)
        // 하지만 이번에는 쿼리문(SQL문)으로 전부 처리 해보겠다

        List<Complain> complain = complainRepo.findByUserName(userName);
        // 민원글 전체 다 가져온 다음에 ListDTO 객체로 변환 시켜준다.
        // ListDTO객체들을 ArrayList에 담아준다.

        List<ListDTO> listDTOs = new ArrayList<>();

        for(Complain row : complain){
            ListDTO listDTO = new ListDTO(row);
            listDTOs.add(listDTO);
        }
        return listDTOs;
    }

    public DetailDTO getDetail(long id) {
        // 상세페이지의 id로 조회
        Complain complain = complainRepo.findById(id);
        // 이미지 테이블
        List<ComplainFile> complainFile = complainImageService.getImgs(complain.getId());
        // 답변 테이블에서 조회
        ComplainReply complainReply = replyService.getReply(complain.getId());

        DetailDTO detailDTO = new DetailDTO(complain,complainReply);

        // 이미지 있다면 get 메서드로 추가해준다.
        List<ImgDTO> imgDTOs = new ArrayList<>();
        for(ComplainFile row : complainFile){
            ImgDTO imgDTO = new ImgDTO(row);
            imgDTOs.add(imgDTO);
        }
        detailDTO.setImgDtos(imgDTOs);
        return detailDTO;
    }
}
