package cn.calendo.tcmdistribution.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 定时任务dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskIdDTO {
    /**
     * 任务id号
     */
    private String taskId;

    /**
     * 任务状态：true:启动；false:停止
     */
    private Boolean status;
}
