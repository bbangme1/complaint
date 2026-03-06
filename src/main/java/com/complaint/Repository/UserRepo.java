package com.complaint.Repository;

import com.complaint.Entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepo {

    public int save(User user);

    public User findById(String userId);
}
