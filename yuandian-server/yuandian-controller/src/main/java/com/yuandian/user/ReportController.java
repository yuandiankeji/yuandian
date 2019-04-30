package com.yuandian.user;

import com.yuandian.core.common.ResultModel;
import com.yuandian.entity.ReportPO;
import com.yuandian.service.ReportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: luyufeng
 * @Date: 2019/4/29
 */

@RestController
@RequestMapping(value = "report")
@EnableSwagger2
public class ReportController {

    @Autowired
    private ReportService reportService;

    @ApiOperation("举报用户")
    @PostMapping("/report")
    public ResultModel reportUser(@ApiParam("举报信息") @RequestBody ReportPO reportPO) {
        reportService.insertReport(reportPO);
        return ResultModel.ok();
    }
}
