package cn.calendo.tcmdistribution.controller.presInfo;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.dto.RmvPresInfoDTO;
import cn.calendo.tcmdistribution.service.IPresInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 撤销操作接口
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/pres_info")
public class RptPresInfo {

    @Autowired
    private IPresInfoService PresInfoService;

    /**
     * 根据指定id还原某条已删除的邮政报文（由于需要操作字段isDeleted，因此传入DTO）
     *
     * @param id        唯一标识
     * @return R对象
     */
    @PutMapping("/rpt")
    public R rptPresInfoById(@RequestParam(value = "id") String id) {
        //设置dto，传入删除的参数
        RmvPresInfoDTO rmvPresInfoDTO = new RmvPresInfoDTO();
        rmvPresInfoDTO.setId(id);
        //调用方法
        boolean res = PresInfoService.repentPresInfoById(rmvPresInfoDTO);
        if (!res) {
            log.error("恢复失败");
            return R.error(500, "恢复失败", new Date());
        }
        log.info("恢复成功");
        return R.success(200, "恢复成功", new Date());
    }
    
}
