package com.yuandian.service.impl;

import com.yuandian.entity.ReportPO;
import com.yuandian.mapper.ReportPOMapper;
import com.yuandian.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: luyufeng
 * @Date: 2019/4/29
 * 举报
 */

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportPOMapper reportPOMapper;

    @Override
    public void insertReport(ReportPO reportPO) {
        reportPOMapper.insert(reportPO);
    }
}
