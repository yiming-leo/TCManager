package cn.calendo.tcmdistribution.controller.shipInfo;

import cn.calendo.tcmdistribution.service.IShipInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/ship_info/send")
public class SendShipInfo {

    @Autowired
    private IShipInfoService shipInfoService;



}
