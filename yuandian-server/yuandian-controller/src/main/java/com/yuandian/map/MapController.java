package com.yuandian.map;

import com.yuandian.core.common.ResultModel;
import com.yuandian.core.common.ResultStatus;
import com.yuandian.domain.MapGoods;
import com.yuandian.entity.GoodsPo;
import com.yuandian.entity.ItemPo;
import com.yuandian.service.GoodsService;
import com.yuandian.service.MapService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author: luyufeng
 * @Date: 2019/7/21
 */
@RestController
@RequestMapping(value = "map")
@EnableSwagger2
public class MapController {

    @Autowired
    private MapService mapService;

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("在地图某点放入物品")
    @RequestMapping(value = "/put", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel put(@RequestParam double longitude,@RequestParam double latitude, @RequestParam long gid, @RequestParam long itemId) {
        GoodsPo goodsPo = goodsService.getGoodsById(gid);
        if (goodsPo == null) {
            return ResultModel.error(ResultStatus.GOODS_NOT_FOUND);
        }

        mapService.putGoods(longitude, latitude, itemId, gid);
        ResultModel resultModel = ResultModel.ok();
        return resultModel;
    }

    @ApiOperation("根据经纬度获取附近Nm的物品")
    @RequestMapping(value = "/getNearGoods", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel getNearGoods(@RequestParam double longitude,@RequestParam double latitude, @RequestParam int range) {
        List<MapGoods> nearGoods = mapService.getNearByGoods(longitude, latitude, range);
        ResultModel resultModel = ResultModel.ok();
        resultModel.setContent(nearGoods);
        return resultModel;
    }
}
