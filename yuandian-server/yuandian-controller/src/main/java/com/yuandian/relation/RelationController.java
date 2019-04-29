package com.yuandian.relation;

import com.yuandian.core.common.ResultModel;
import com.yuandian.domain.UserPO;
import com.yuandian.entity.RelationPO;
import com.yuandian.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: luyufeng
 * @Date: 2019/4/29
 */

@RestController
@RequestMapping(value = "/relation")
public class RelationController {

    @Autowired
    private RelationService relationService;

    /**
     * 获取所有好友
     * @param uid
     * @return
     */
    @GetMapping("/friends/{uid}")
    public ResultModel getAllFriends(@PathVariable long uid) {
        List<UserPO> allFriends = relationService.selectFriends(uid);
        ResultModel resultModel = ResultModel.ok();
        resultModel.setContent(allFriends);
        return resultModel;
    }

    /**
     * 更新好友关系
     * @param relationPO
     * @return
     */
    @PostMapping("/update/{uid}")
    public ResultModel updateRelation(@RequestBody RelationPO relationPO) {
        relationService.updateRelation(relationPO);
        return ResultModel.ok();
    }
}
