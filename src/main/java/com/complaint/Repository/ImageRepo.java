package com.complaint.Repository;

import com.complaint.Entity.ComplainFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageRepo {

    void save(ComplainFile complainFile);

    List<ComplainFile> findById(long id);
}
