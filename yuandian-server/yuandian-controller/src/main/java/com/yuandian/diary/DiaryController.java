package com.yuandian.diary;

import com.robert.vesta.service.bean.Id;
import com.robert.vesta.service.intf.IdService;
import com.yuandian.core.common.ResultModel;
import com.yuandian.core.common.ResultStatus;
import com.yuandian.entity.DiaryPO;
import com.yuandian.entity.GoodsPo;
import com.yuandian.entity.UserPO;
import com.yuandian.service.DiaryService;
import com.yuandian.service.GoodsService;
import com.yuandian.service.UserService;
import com.yuandian.utils.RequestUtil;
import com.yuandian.vo.DiaryVO;
import com.yuandian.vo.PublisherVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@RestController
@RequestMapping(value = "diary")
@EnableSwagger2
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @Autowired
    private UserService userService;

    @Autowired
    private IdService idService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/publish", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation("发布流浪日记")
    public ResultModel publishDiary(@RequestParam String content, @RequestParam long goodsId) {
        GoodsPo goodsPo = goodsService.getGoodsById(goodsId);
        if (goodsPo == null) {
            ResultModel resultModel = ResultModel.error(ResultStatus.GOODS_NOT_FOUND);
            return resultModel;
        }
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
    public ResultModel getDiaryFeeds(@RequestParam long goodsId, @RequestParam int page) {
        GoodsPo goodsPo = goodsService.getGoodsById(goodsId);
        if (goodsPo == null) {
            ResultModel resultModel = ResultModel.error(ResultStatus.GOODS_NOT_FOUND);
            return resultModel;
        }
        List<DiaryVO> list = getDiaryVos(goodsId);

        int pageSize = 10;
        List<DiaryVO> diaryVOS = list.subList(Math.min((page - 1) * pageSize, list.size()),Math.min((page - 1) * pageSize + pageSize, list.size()));
        ResultModel resultModel = ResultModel.ok();
        Map<String, Object> result = new HashMap<>();
        result.put("list", diaryVOS);
        result.put("total", list.size());
        resultModel.setContent(diaryVOS);
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
            long uid = po.getUid();
            UserPO userPO = userService.selectUserById(uid);
            PublisherVo publisherVo = new PublisherVo();
            publisherVo.setUid(userPO.getUid());
            publisherVo.setHeadUrl(userPO.getHeadUrl());
            publisherVo.setNickName(userPO.getNickName());
            vo.setPublisher(publisherVo);
            vos.add(vo);
        }
        return vos;
    }
}
