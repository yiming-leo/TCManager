package cn.calendo.tcmpost.controller;

import cn.calendo.tcmpost.common.R;
import cn.calendo.tcmpost.dto.PostMedDTO;
import cn.calendo.tcmpost.dto.RcvShipInfoDTO;
import cn.calendo.tcmpost.service.IPostMedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 接收医院的报文
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/poster")
public class RcvPostMed {

    @Autowired
    private IPostMedService postMedService;

    @PostMapping("/from_tcmd")
    public R receivePostMedAndCheckAndSave(@RequestBody RcvShipInfoDTO rcvShipInfoDTO) {
        //接收报文
        PostMedDTO postMedDTO = postMedService.receivePostMedicine(rcvShipInfoDTO);
        if (postMedDTO == null) {
            return R.error(404, "接收失败", new Date());
        }
        //审核报文
        //保存报文
        boolean saveRes = postMedService.savePostMedicine(postMedDTO);
        if (!saveRes) {
            return R.error(404, "保存失败", new Date());
        }
        return R.success(200, "接收成功", new Date());
    }

}
