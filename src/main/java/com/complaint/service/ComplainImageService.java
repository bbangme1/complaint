package com.complaint.service;

import com.complaint.Entity.ComplainFile;
import com.complaint.Repository.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

@Service
public class ComplainImageService {
    @Autowired
    private ImageRepo imageRepo;

    @Value("${filePath}")
    String uploadPath;

    public void saveImg(List<MultipartFile> multipartFileList, Long id) throws Exception {
        String imgName = "";
        String imgPath = "";

        for (MultipartFile multipartFile : multipartFileList) {
            if(StringUtils.isEmpty(multipartFile.getOriginalFilename())){
                return; // input 태그에 이미지를 선택해야지만 오리지날네임을 가진다.
                // 오리지날네임 없는경우는 이미지선택안된 input태그이므로
                // 아래 쪽 코드는 진행 하면 안된다.
            }
            // 이미지 업로드를 위해 이미지 이름 변경
            UUID uuid = UUID.randomUUID();
            // 이미지 확장자 추출하여 uuid 이름과 합치기
            // 이렇게 만들어진 이름이 실제 업로드 저장될 이미지 이름이다.
            String ext = multipartFile.getOriginalFilename()
                    .substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            String saveFname = uuid.toString() + ext;

            // 업로드 경로와 이미지 이름
            String fileUpload = uploadPath + "/" + saveFname;
            // 진짜로 업로드 저장 시키기
            FileOutputStream fos = new FileOutputStream(fileUpload);
            fos.write(multipartFile.getBytes()); // 저장할 파일 크기
            fos.close(); // 파일 저장 했으면 반드시 닫기

            // 업로드 이미지에대해 테이블에 저장
            // 테이블에는 이미지 경로 및 이름을 저장한다.
            ComplainFile complainFile = new ComplainFile();
            complainFile.setComplainId(id);
            complainFile.setFileName(saveFname);
            complainFile.setFilePath("/img/"+saveFname);

            imageRepo.save(complainFile);
        }
    }

    public List<ComplainFile> getImgs(long id) {
        List<ComplainFile> complainFile = imageRepo.findById(id);
        return complainFile;
    }
}
