package cn.calendo.tcmdistribution.controller.presInfo;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.dto.BatchSaveFacDTO;
import cn.calendo.tcmdistribution.entity.PresInfo;
import cn.calendo.tcmdistribution.service.IPresInfoService;
import cn.calendo.tcmdistribution.service.IShipInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 分配药厂接口
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/pres_info/dtb")
public class DtbPresInfo {

    @Autowired
    private IPresInfoService presInfoService;

    @Autowired
    private IShipInfoService shipInfoService;

    /**
     * 自动分配药厂
     *
     * @param batchSaveFacDTO 依据处方信息批量新增邮政报文的dto
     * @return
     */
    @PostMapping("/fac/hand")
    public R distributePres2FactoryByHand(@RequestBody BatchSaveFacDTO batchSaveFacDTO) {
        PresInfo presInfo = presInfoService.queryPresInfoById(batchSaveFacDTO.getId());
        //由于这里的处方内并没有包含邮政报文的很多参数，需要手动添加许多参数
        Integer hit_count = shipInfoService.batchSaveShipInfoByFac(batchSaveFacDTO, presInfo);
        if (!hit_count.equals(batchSaveFacDTO.getFacNumber())) {
            return R.error(404, "药厂分配失败", new Date(), "hit_count:" + hit_count);
        }
        boolean markRes = presInfoService.adoptPresInfoMark(batchSaveFacDTO.getId(),batchSaveFacDTO.getFacNumber());
        if (!markRes) {
            return R.error(404, "药厂分配成功，记录更新失败", new Date());
        }
        return R.success(200, "药厂分配成功，请在报文信息处查看", new Date());
    }

    /**
     * 自动分配药厂
     *
     * @param facNumber 此处方的药厂数量
     * @return R对象
     */
    public R distributePres2FactoryAuto(@RequestParam(value = "facNumber") String facNumber) {

//        if (!res) {
//            return R.error(404, "药厂分配失败", new Date());
//        }
        return R.success(200, "药厂分配通过", new Date());
    }

}
