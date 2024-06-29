package com.Yu.Pan.schedule.test01.test;

import com.Yu.Pan.schedule.ScheduleTask;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/29 10:13
 */
@Component
public class SimpleScheduleTask implements ScheduleTask {
    @Override
    public String getName() {
        return "SimpleScheduleTask";
    }

    @Override
    public void run() {
        System.out.println("SimpleScheduleTask is running...");
    }
}
