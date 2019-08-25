package com.yuandian.item;

import com.robert.vesta.service.intf.IdService;
import com.yuandian.core.common.ResultModel;
import com.yuandian.core.common.ResultStatus;
import com.yuandian.entity.ItemPo;
import com.yuandian.service.ItemService;
import com.yuandian.service.MapService;
import com.yuandian.utils.RequestUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author: luyufeng
 * @Date: 2019/6/10
 * 背包
 */

@RestController
@RequestMapping(value = "item")
@EnableSwagger2
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private MapService mapService;

    @Autowired
    private IdService idService;

    @ApiOperation("获取用户背包内所有物品")
    @RequestMapping(value = "/getAllGoods", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel getAllGoods(@RequestParam long uid) {
        List<ItemPo> allItems = itemService.getAllItems(uid);
        ResultModel resultModel = ResultModel.ok();
        resultModel.setContent(allItems);
        return resultModel;
    }

    @ApiOperation("将地图上某一物品加入背包物品")
    @RequestMapping(value = "/insertToBag", method = {RequestMethod.POST,RequestMethod.GET})
    public ResultModel insertToBag(@RequestParam double longitude,
                                   @RequestParam double latitude,
                                   @RequestParam long gid,
                                   @RequestParam long itemId) {
        boolean hasGoods = mapService.hasGoods(longitude, latitude,itemId, gid);
        if (!hasGoods) {
            return ResultModel.error(ResultStatus.MAP_GOODS_NOT_EXIST);
        }
        ItemPo itemPo = new ItemPo();
        itemPo.setGid(gid);
        itemPo.setNum(1L);
        itemPo.setUid(RequestUtil.getCurrentUid());
        itemPo.setId(itemId);
        itemService.insertToBag(itemPo);
        mapService.takeFromMap(longitude, latitude,itemId, gid);
        ResultModel resultModel = ResultModel.ok();
        return resultModel;
    }

}
