package com.complaint.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplainWriteDTO {
    @NotBlank( message = "제목은 필수다")
    private String title;

    @NotBlank( message = "내용은 필수다")
    private String content;

    @NotBlank( message = "민원 유형은 필수다")
    private String category;

}
