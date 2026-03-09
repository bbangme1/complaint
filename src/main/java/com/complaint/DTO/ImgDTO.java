package com.complaint.DTO;

import com.complaint.Entity.ComplainFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImgDTO {
    private String url;
    private String imgName;

    public ImgDTO(ComplainFile complainFile) {
        this.url = complainFile.getFilePath();
        this.imgName = complainFile.getFileName();
    }
}
