package com.yuandian.diary;

import com.robert.vesta.service.bean.Id;
import com.robert.vesta.service.intf.IdService;
import com.yuandian.core.common.ResultModel;
import com.yuandian.core.common.ResultStatus;
import com.yuandian.entity.DiaryPO;
import com.yuandian.entity.UserPO;
import com.yuandian.service.DiaryService;
import com.yuandian.service.UserService;
import com.yuandian.utils.RequestUtil;
import com.yuandian.vo.DiaryVO;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "diary")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @Autowired
    private UserService userService;

    @Autowired
    private IdService idService;

    @RequestMapping(value = "/publish", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation("发布流浪日记")
    public ResultModel publishDiary(@RequestParam String content, @RequestParam long goodsId) {
        long uid = RequestUtil.getCurrentUid();

        UserPO userPO = userService.selectUserById(uid);
        if (userPO == null) {
            ResultModel resultModel = new ResultModel(ResultStatus.USER_NOT_FOUND);
            return resultModel;
        }
        if (StringUtils.isBlank(content)) {
            ResultModel resultModel = new ResultModel(ResultStatus.CANNOT_NULL);
            return resultModel;
        }
        DiaryPO diaryPO = new DiaryPO();
        diaryPO.setGoodsId(goodsId);
        diaryPO.setContent(content);
        diaryPO.setUid(uid);
        diaryPO.setGoodsType(0);
        diaryService.insert(diaryPO);
        return ResultModel.ok();
    }

    @RequestMapping(value = "/getDiaryFeed", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation("根据宝物id获取流浪日记列表")
    public ResultModel getDiaryFeeds(@RequestParam long goodsId) {
        List<DiaryVO> list = getDiaryVos(goodsId);
        ResultModel resultModel = ResultModel.ok();
        resultModel.setContent(list);
        return resultModel;
    }

    private List<DiaryVO> getDiaryVos(long goodsId) {
        List<DiaryPO> list = diaryService.getDiarysByGoodsId(goodsId);
        List<DiaryVO> vos = new ArrayList<>();
        for (DiaryPO po : list) {
            DiaryVO vo = new DiaryVO();
            BeanUtils.copyProperties(po, vo);
            Id metaId = idService.expId(po.getId());
            Date publishTime = idService.transTime(metaId.getTime());
            vo.setPublishTime(publishTime);
            vos.add(vo);
        }
        return vos;
    }
}
