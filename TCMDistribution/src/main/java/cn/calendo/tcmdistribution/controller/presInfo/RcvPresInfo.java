package cn.calendo.tcmdistribution.controller.presInfo;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.dto.RcvPresInfoDTO;
import cn.calendo.tcmdistribution.service.IPresInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 接收信息接口
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/pres_info")
public class RcvPresInfo {

    @Autowired
    private IPresInfoService presInfoService;

    /**
     * 接收医生站发送的处方信息的接口
     *
     * @param rcvPresInfoDTO 处方信息DTO
     * @return R对象
     */
    @PostMapping("/rcv")
    public R receivePresFromDoctor(@RequestBody RcvPresInfoDTO rcvPresInfoDTO) {
        log.info(String.valueOf(rcvPresInfoDTO));
        boolean res = presInfoService.checkPresInfo(rcvPresInfoDTO);
        if (!res) {
            return R.error(400, "审核失败", new Date(), rcvPresInfoDTO);
        }
        boolean res2 = presInfoService.saveRcvPresInfoDTO2PresInfoById(rcvPresInfoDTO);
        if (!res2) {
            return R.error(404, "审核通过，保存失败", new Date(), rcvPresInfoDTO);
        }
        return R.success(200, "审核通过", new Date(), rcvPresInfoDTO);
    }

}
