package com.yuandian.relation;

import com.yuandian.core.common.ResultModel;
import com.yuandian.entity.RelationPO;
import com.yuandian.entity.UserPO;
import com.yuandian.service.RelationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author: luyufeng
 * @Date: 2019/4/29
 */

@RestController
@RequestMapping(value = "/relation")
@EnableSwagger2
public class RelationController {

    @Autowired
    private RelationService relationService;

    @GetMapping("/friends/{uid}")
    @ApiOperation("获取所有好友信息")
    public ResultModel getAllFriends(@ApiParam("用户id") @PathVariable long uid) {
        List<UserPO> allFriends = relationService.selectFriends(uid);
        ResultModel resultModel = ResultModel.ok();
        resultModel.setContent(allFriends);
        return resultModel;
    }

    @ApiOperation("更新好友关系")
    @PostMapping("/update")
    public ResultModel updateRelation(@ApiParam("好友关系信息") @RequestBody RelationPO relationPO) {
        relationService.updateRelation(relationPO);
        return ResultModel.ok();
    }
}
