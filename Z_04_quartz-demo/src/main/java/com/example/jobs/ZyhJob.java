package com.example.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by zhangyuhong
 * Date:2020/12/20
 */
public class ZyhJob implements Job {
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.out.println("zyh----ZyhJob-----定时任务");
	}
}
