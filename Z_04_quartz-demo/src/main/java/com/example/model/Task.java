package com.example.model;

import lombok.Builder;
import lombok.Data;
import org.quartz.Job;
import org.quartz.JobKey;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @ClassName Task
 * @Description 定时任务基本属性
 */
@Data
//@Builder
public class Task {

    public Task() {
    }

    /**
     * 任务名称
     */
    @NotEmpty(message = "任务名称不能为空")
    private String name;

    /**
     * 任务分组
     */
    @NotEmpty(message = "任务分组不能为空")
    private String goup;

    /**
     * corn表达式
     */
    @NotEmpty(message = "定时任务的表达式不能为空")
    private String cron;

    /**
     * 任务描述
     */
    private String desc;

    /**
     * 执行任务的逻辑类
     */
    @NotNull(message = "执行任务的逻辑类名不能为空")
    private String jobClass;

    /**
     * 元数据
     */
    private Map<?,?> jobDataMap;

    public JobKey getJobKey(){
        return JobKey.jobKey(this.name,this.goup);
    }
}
