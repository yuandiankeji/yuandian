package com.yuandian.user;

import com.yuandian.core.common.ResultModel;
import com.yuandian.entity.ReportPO;
import com.yuandian.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: luyufeng
 * @Date: 2019/4/29
 */

@RestController
@RequestMapping(value = "report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/report")
    public ResultModel reportUser(@RequestBody ReportPO reportPO) {
        reportService.insertReport(reportPO);
        return ResultModel.ok();
    }
}
