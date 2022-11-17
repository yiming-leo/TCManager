package cn.calendo.tcmdistribution.controller.presInfo;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.entity.PresInfo;
import cn.calendo.tcmdistribution.service.IPresInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/pres_info/get")
public class GetPresInfo {

    @Autowired
    private IPresInfoService presInfoService;

    /**
     * 查询所有审核通过的处方
     *
     * @return R对象
     */

    @GetMapping("/all")
    public R getPresInfoAll(){
        List<PresInfo> presInfos = presInfoService.queryPresInfoAll();
        if (presInfos.size() == 0) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), presInfos);
    }

    /**
     * 根据id查询某条报文
     *
     * @param id 报文唯一序列号
     * @return R对象
     */
    @GetMapping("/by_id")
    public R getShipInfoById(@RequestParam(value = "id") Long id) {
        PresInfo presInfo = presInfoService.queryPresInfoById(id);
        if (presInfo == null) {
            return R.error(404, "不存在", new Date());
        }
        return R.success(200, "查询成功", new Date(), presInfo);
    }


}
