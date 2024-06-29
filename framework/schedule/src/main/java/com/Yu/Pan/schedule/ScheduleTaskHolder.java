package com.Yu.Pan.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ScheduledFuture;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/29 9:15
 */
@AllArgsConstructor
@Data
public class ScheduleTaskHolder {
    private ScheduleTask scheduleTask;
    private ScheduledFuture<?> scheduledFuture;

}
