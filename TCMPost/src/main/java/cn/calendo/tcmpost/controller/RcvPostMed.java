package cn.calendo.tcmpost.controller;

import cn.calendo.tcmpost.common.R;
import cn.calendo.tcmpost.dto.PostMedDTO;
import cn.calendo.tcmpost.dto.RcvShipInfoDTO;
import cn.calendo.tcmpost.service.IPostMedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/poster")
public class RcvPostMed {

    @Autowired
    private IPostMedService postMedService;

    @PostMapping("/from_tcmd")
    public R receivePostMedicine(@RequestBody RcvShipInfoDTO rcvShipInfoDTO) {
        PostMedDTO postMedDTO = postMedService.receivePostMedicine(rcvShipInfoDTO);
        if (postMedDTO == null) {
            return R.error(404, "接收失败", new Date());
        }
        return R.success(200, "接收成功", new Date());
    }

}
