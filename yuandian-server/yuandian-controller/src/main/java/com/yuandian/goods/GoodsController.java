package com.yuandian.goods;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.yuandian.core.common.ResultModel;
import com.yuandian.core.common.ResultStatus;
import com.yuandian.entity.GoodsPo;
import com.yuandian.service.GoodsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author: luyufeng
 * @Date: 2019/7/6
 */
@RestController
@RequestMapping("goods")
@EnableSwagger2
@EnableApolloConfig
public class GoodsController {

    @Value("${testluyufeng}")
    private String testluyufeng;

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("获取宝物简介")
    @RequestMapping(value = "/slogan", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel getSlogan(@ApiParam("宝物id") @RequestParam long goodsId) {
        GoodsPo goodsPo = goodsService.getGoodsById(goodsId);
        if (goodsPo == null) {
            ResultModel resultModel = ResultModel.error(ResultStatus.GOODS_NOT_FOUND);
            return resultModel;
        }
        ResultModel resultModel = ResultModel.ok();
        resultModel.setContent(goodsPo);
        return resultModel;
    }

    @ApiOperation("获取所有物品资源列表")
    @RequestMapping(value = "/getAllGoods", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel getAllGoods() {
        List<GoodsPo> allGoods = goodsService.getAllGoods();
        ResultModel resultModel = ResultModel.ok();
        resultModel.setContent(allGoods);
        return resultModel;
    }

    @RequestMapping(value = "/test", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String test() {
        return testluyufeng;
    }
}
