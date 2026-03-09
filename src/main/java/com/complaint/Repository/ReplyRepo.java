package com.complaint.Repository;

import com.complaint.Entity.ComplainReply;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyRepo {
    ComplainReply findById(long id);
}
