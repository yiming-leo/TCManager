package cn.calendo.tcmdistribution.controller.schedule;

import cn.calendo.tcmdistribution.common.R;
import cn.calendo.tcmdistribution.dto.TaskIdDTO;
import cn.calendo.tcmdistribution.service.IScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 定时任务，定时查询处方信息
 */
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/auto_refresh")
public class AutoRefresh {

    @Autowired
    private IScheduleService scheduleService;

    @PostMapping("/start")
    public R startAutoRefreshPresInfo(@RequestParam("taskId") String id, @RequestParam("expression") String expression) {
        TaskIdDTO taskIdDTO = scheduleService.scheduleStart(id, expression);
        return R.success(200, "自动刷新处方" + id + "启动成功", new Date(), taskIdDTO);
    }

    @PostMapping("/stop")
    public R stopAutoRefreshPresInfo(@RequestParam("taskId") String id) {
        TaskIdDTO taskIdDTO = scheduleService.scheduleStop(id);
        return R.success(200, "自动刷新处方" + id + "停止成功", new Date(), taskIdDTO);
    }

}
