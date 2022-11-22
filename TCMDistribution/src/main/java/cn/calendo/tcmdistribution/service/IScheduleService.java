package cn.calendo.tcmdistribution.service;

import cn.calendo.tcmdistribution.dto.TaskIdDTO;

/**
 * 任务调度接口
 */
public interface IScheduleService {

    /**
     * 任务启动
     *
     * @param taskId     任务id
     * @param expression cron表达式
     * @return TaskIdDTO TaskIdDTO对象
     */
    TaskIdDTO scheduleStart(String taskId, String expression);

    /**
     * 任务停止
     *
     * @param taskId 任务id
     * @return TaskIdDTO TaskIdDTO对象
     */
    TaskIdDTO scheduleStop(String taskId);
}
