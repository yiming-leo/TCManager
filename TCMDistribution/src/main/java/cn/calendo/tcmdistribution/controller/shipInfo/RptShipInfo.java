package cn.calendo.tcmdistribution.controller.shipInfo;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.dto.RmvShipInfoDTO;
import cn.calendo.tcmdistribution.service.IShipInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/ship_info")
public class RptShipInfo {

    @Autowired
    private IShipInfoService shipInfoService;

    /**
     * 根据指定id还原某条已删除的邮政报文（由于需要操作字段isDeleted，因此传入DTO）
     *
     * @param id        唯一标识
     * @param isDeleted 删除与否
     * @return R对象
     */
    @PutMapping("/rpt")
    public R rptShipInfoById(@RequestParam(value = "id") Long id) {
        //设置dto，传入删除的参数
        RmvShipInfoDTO rmvShipInfoDTO = new RmvShipInfoDTO();
        rmvShipInfoDTO.setId(id);
        //调用方法
        boolean res = shipInfoService.repentShipInfoById(rmvShipInfoDTO);
        if (!res) {
            return R.error(404, "恢复失败", new Date());
        }
        return R.success(200, "恢复成功", new Date());
    }

}
