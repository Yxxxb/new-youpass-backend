package com.youpass.service;

import com.youpass.model.NoticeInfo;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.stereotype.Service;

@Service
public interface NoticeService {
    public Result<Object> getNotice(Long id);

    public Result<Object> generateNotice(NoticeInfo noticeInfo);
}
