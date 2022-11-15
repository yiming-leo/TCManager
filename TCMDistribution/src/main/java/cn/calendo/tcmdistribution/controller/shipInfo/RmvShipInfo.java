package cn.calendo.tcmdistribution.controller.shipInfo;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.dto.RmvShipInfoDTO;
import cn.calendo.tcmdistribution.service.IShipInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 移除邮政配药报文接口
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/ship_info")
public class RmvShipInfo {

    @Autowired
    private IShipInfoService shipInfoService;

    /**
     * 根据指定id删除某条已传送的邮政报文（由于需要操作字段isDeleted，因此传入DTO）
     *
     * @param id        唯一标识
     * @param isDeleted 删除与否
     * @return R对象
     */
    @PutMapping("/rmv")
    public R rmvShipInfoById(@RequestParam(value = "id") Long id) {
        //设置dto，传入删除的参数
        RmvShipInfoDTO rmvShipInfoDTO = new RmvShipInfoDTO();
        rmvShipInfoDTO.setId(id);
        //调用方法
        boolean res = shipInfoService.removeShipInfoById(rmvShipInfoDTO);
        if (!res) {
            return R.error(404, "删除失败", new Date());
        }
        return R.success(200, "删除成功", new Date());
    }

}
