package com.complaint.Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Complain {
    private long id;
    private String title;
    private String content;
    private long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String category;
    private String status;
}
