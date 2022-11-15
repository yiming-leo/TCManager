package cn.calendo.tcmdistribution.controller.shipInfo;

import cn.calendo.tcmdistribution.service.IShipInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送并保存邮政配药报文接口
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/ship_info/snd")
public class SndShipInfo {

    @Autowired
    private IShipInfoService shipInfoService;



}
