package cn.calendo.tcmdistribution.controller.shipInfo;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.dto.SndShipInfoDTO;
import cn.calendo.tcmdistribution.entity.ShipInfo;
import cn.calendo.tcmdistribution.service.IShipInfoService;
import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 更新审核记录（审核成功：isDeleted为1，起始为0）
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/ship_info/exm")
public class ExmShipInfo {

    @Autowired
    private IShipInfoService shipInfoService;

    /**
     * 指定id的行审核通过并发送至邮政（isDeleted改为1）
     *
     * @param id shipInfo的id
     * @return R对象
     */
    @PutMapping("/single")
    public R examShipInfoById(@RequestParam(value = "id") Long id) {
        ShipInfo shipInfo = shipInfoService.queryShipInfoById(id);//查出相应报文实体类
        if (shipInfo == null) {
            return R.error(404, "暂无此报文", new Date());
        }
        SndShipInfoDTO sndShipInfoDTO = new SndShipInfoDTO();
        BeanUtil.copyProperties(shipInfo, sndShipInfoDTO, "isDeleted");//bean拷贝
        boolean res = shipInfoService.sendShipInfo(sndShipInfoDTO);//发送dto
        if (!res) {
            return R.error(500, "查得此记录但发送失败", new Date());
        }
        boolean adoptRes = shipInfoService.adoptShipInfoById(id);
        if (!adoptRes) {
            return R.error(500, "发送成功但记录更新失败", new Date());
        }
        return R.success(200, "审核通过、发送成功、记录更新成功", new Date(), id);
    }

}
