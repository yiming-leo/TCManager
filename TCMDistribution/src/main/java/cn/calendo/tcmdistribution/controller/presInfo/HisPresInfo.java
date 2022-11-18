package cn.calendo.tcmdistribution.controller.presInfo;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.entity.PresInfo;
import cn.calendo.tcmdistribution.service.IPresInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 处方信息历史记录接口
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/pres_info/his")
public class HisPresInfo {

    @Autowired
    private IPresInfoService presInfoService;

    /**
     * 查询所有已分配药厂的处方的历史记录
     * @return R对象
     */
    @GetMapping("/all")
    public R getHistoryPresInfoAll(){
        List<PresInfo> presInfos = presInfoService.queryHistoryPresInfoAll();
        if (presInfos.size() == 0) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), presInfos);
    }

    /**
     * 根据id查询某条已分配药厂的处方
     * @param id 唯一码
     * @return R对象
     */
    @GetMapping("/by_id")
    public R getHistoryShipInfoById(@RequestParam(value = "id") Long id) {
        PresInfo presInfo = presInfoService.queryHistoryPresInfoById(id);
        if (presInfo == null) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), presInfo);
    }

}
