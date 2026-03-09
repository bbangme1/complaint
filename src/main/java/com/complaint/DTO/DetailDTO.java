package com.complaint.DTO;

import com.complaint.Entity.Complain;
import com.complaint.Entity.ComplainReply;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DetailDTO {
    private long id; // 수정을위해 primary key 필요
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String category;
    private String status;
    private String reply; // 민원 답변내용
    private LocalDateTime replyAt; // 답변 작성일
    private List<ImgDTO> imgDtos;

    public DetailDTO(Complain complain, ComplainReply complainReply){
        this.id = complain.getId();
        this.title = complain.getTitle();
        this.content = complain.getContent();
        this.createdAt = complain.getCreatedAt();
        this.category = complain.getCategory();
        this.status = complain.getStatus();
        if(complainReply != null){
            this.reply = complainReply.getContent();
            this.replyAt = complainReply.getCreatedAt();
        }
    }
}
