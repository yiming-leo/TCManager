package cn.calendo.tcmdistribution.service.impl;

import cn.calendo.tcmdistribution.dto.TaskIdDTO;
import cn.calendo.tcmdistribution.service.IPresInfoService;
import cn.calendo.tcmdistribution.service.IScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 任务调度实现类
 */
@Slf4j
@Service
public class ScheduleServiceImpl implements IScheduleService {

    private static Logger logger = LoggerFactory.getLogger(Scheduled.class);

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private IPresInfoService presInfoService;

    public static Map<String, ScheduledFuture<?>> taskmap = new HashMap<>();

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    /**
     * 任务启动
     *
     * @param taskId     任务id
     * @param expression cron表达式
     * @return TaskIdDTO
     */
    @Override
    public TaskIdDTO scheduleStart(String taskId, String expression) {
        TaskThread tt = new TaskThread(taskId);
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(tt, new CronTrigger(expression));
        //任务taskIdDTO
        TaskIdDTO taskIdDTO = new TaskIdDTO();//装入任务id号返回
        taskIdDTO.setTaskId(taskId);
        taskIdDTO.setStatus(true);
        taskmap.put(taskId, future);
        log.info("任务号:" + taskId + "启动成功");
        return taskIdDTO;
    }

    /**
     * 任务停止
     *
     * @param taskId 任务id
     * @return TaskIdDTO
     */
    @Override
    public TaskIdDTO scheduleStop(String taskId) {
        ScheduledFuture<?> future = taskmap.get(taskId);
        if (future != null) {
            future.cancel(true);
        } else {
            return null;
        }
        //任务taskIdDTO
        TaskIdDTO taskIdDTO = new TaskIdDTO();//装入任务id号返回
        taskIdDTO.setTaskId(taskId);
        taskIdDTO.setStatus(false);
        taskmap.remove(taskId);
        log.info("任务号:" + taskId + "停止成功");
        return taskIdDTO;
    }

    //内部类
    public class TaskThread implements Runnable {

        private String id;
        private String expression;

        /**
         * 处方自动刷新
         *
         * @param id 定时任务id
         */
        public TaskThread(String id) {
            this.id = id;
        }
//
//        /**
//         * 处方自动刷新
//         *
//         * @param id         定时任务id
//         * @param expression cron表达式
//         */
//        public TaskThread(String id, String expression) {
//            this.id = id;
//            this.expression = expression;
//        }

        @Override
        public void run() {
            //根据传来的参数执行要定时的任务
            presInfoService.queryPresInfoAll();
            logger.info(id + new Date());
        }

    }
}
