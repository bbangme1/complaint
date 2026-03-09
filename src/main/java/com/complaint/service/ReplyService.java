package com.complaint.service;

import com.complaint.Entity.ComplainReply;
import com.complaint.Repository.ReplyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepo replyRepo;

    public ComplainReply getReply(long id) {
        return replyRepo.findById(id);
    }
}
