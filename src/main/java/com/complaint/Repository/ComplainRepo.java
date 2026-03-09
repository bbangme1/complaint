package com.complaint.Repository;

import com.complaint.Entity.Complain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComplainRepo {

    void save(Complain complain);

    Complain find(long userId);

    List<Complain> findByUserName(String userName);

    Complain findById(long id);
}
