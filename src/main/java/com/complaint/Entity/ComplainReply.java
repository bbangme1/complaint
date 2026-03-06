package com.complaint.Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ComplainReply {
    private Long id;
    private long complainId;
    private long adminId;
    private String content;
    private LocalDateTime createdAt;
}
