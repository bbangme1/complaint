package com.complaint.DTO;

import com.complaint.Entity.Complain;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ListDTO {
    private Long id;
    private String title;
    private String status;
    private LocalDateTime createdAt;

    public ListDTO(Complain complain){
        this.id = complain.getId();
        this.title = complain.getTitle();
        this.status = complain.getStatus();
        this.createdAt = complain.getCreatedAt();
    }
}
