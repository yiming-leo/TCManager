package cn.calendo.tcmdistribution.controller.presInfo;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.dto.RmvPresInfoDTO;
import cn.calendo.tcmdistribution.service.IPresInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 移除处方信息接口
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/pres_info")
public class RmvPresInfo {

    @Autowired
    private IPresInfoService PresInfoService;

    /**
     * 根据指定id删除某条已传送的邮政报文（由于需要操作字段isDeleted，因此传入DTO）
     *
     * @param id 唯一标识
     * @return R对象
     */
    @PutMapping("/rmv")
    public R rmvPresInfoById(@RequestParam(value = "id") String id) {
        //设置dto，传入删除的参数
        RmvPresInfoDTO rmvPresInfoDTO = new RmvPresInfoDTO();
        rmvPresInfoDTO.setId(id);
        //调用方法
        boolean res = PresInfoService.removePresInfoById(rmvPresInfoDTO);
        if (!res) {
            log.error("删除失败");
            return R.error(500, "删除失败", new Date());
        }
        log.info("删除成功");
        return R.success(200, "删除成功", new Date());
    }

    /**
     * 永久删除指定id的已传送的邮政报文
     * @param id 唯一标识
     * @return R对象
     */
    @DeleteMapping("/fov_rmv")
    public R foreverRmvPresInfoById(@RequestParam(value = "id") String id){
        boolean res = PresInfoService.foreverRemovePresInfoById(id);
        if (!res) {
            log.error("删除失败");
            return R.error(500, "删除失败", new Date());
        }
        log.info("删除成功");
        return R.success(200, "删除成功", new Date());
    }
}
