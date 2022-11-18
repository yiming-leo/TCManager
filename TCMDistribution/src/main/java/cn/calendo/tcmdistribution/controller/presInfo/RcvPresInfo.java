package cn.calendo.tcmdistribution.controller.presInfo;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.dto.RcvPresInfoDTO;
import cn.calendo.tcmdistribution.listener.rabbitmq.MessageListener;
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

    @Autowired
    private MessageListener messageListener;

    /**
     * 接收医生站发送的处方信息的接口
     *
     * @param rcvPresInfoDTO 处方信息DTO
     * @return R对象
     */
    @PostMapping("/rcv")
    public R receivePresFromDoctor(@RequestBody RcvPresInfoDTO rcvPresInfoDTO) {
        RcvPresInfoDTO receive = messageListener.receive(rcvPresInfoDTO);
        boolean res = presInfoService.checkPresInfo(receive);
        if (!res) {
            log.info("审核失败");
            return R.error(400, "审核失败", new Date(), receive);
        }
        boolean res2 = presInfoService.saveRcvPresInfoDTO2PresInfoById(receive);
        if (!res2) {
            log.error("审核通过，保存失败");
            return R.error(500, "审核通过，保存失败", new Date(), receive);
        }
        log.info("审核通过");
        return R.success(200, "审核通过", new Date(), receive);
    }

}
