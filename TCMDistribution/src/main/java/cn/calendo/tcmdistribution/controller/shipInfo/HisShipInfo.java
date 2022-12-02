package cn.calendo.tcmdistribution.controller.shipInfo;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.entity.ShipInfo;
import cn.calendo.tcmdistribution.service.IShipInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/ship_info/his")
public class HisShipInfo {

    @Autowired
    private IShipInfoService shipInfoService;

    /**
     * 查询所有已分配药厂的处方的历史记录
     *
     * @return R对象
     */
    @GetMapping("/all")
    public R getHistoryShipInfoAll() {
        List<ShipInfo> shipInfos = shipInfoService.queryHistoryShipInfoAll();
        if (shipInfos.size() == 0) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), shipInfos);
    }

    /**
     * 根据id查询某条已分配药厂的处方
     *
     * @param id 唯一码
     * @return R对象
     */
    @GetMapping("/by_id")
    public R getHistoryShipInfoById(@RequestParam(value = "id") String id) {
        ShipInfo shipInfo = shipInfoService.queryHistoryShipInfoById(id);
        if (shipInfo == null) {
            log.info("不存在");
            return R.error(404, "不存在", new Date());
        }
        log.info("查询成功");
        return R.success(200, "查询成功", new Date(), shipInfo);
    }

}
