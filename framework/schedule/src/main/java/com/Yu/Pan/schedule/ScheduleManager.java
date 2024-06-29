package com.Yu.Pan.schedule;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.Yu.Pan.core.exception.FrameworkException;
import com.Yu.Pan.core.utils.UUIDUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/29 9:18
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleManager {
    final ThreadPoolTaskScheduler threadPoolTaskScheduler;
    /*
     *定时任务内部缓存
     * */
    ConcurrentHashMap<String, ScheduleTaskHolder> map = new ConcurrentHashMap<>();

    public String startTask(ScheduleTask task, String cronExpression) {
        ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(task, new CronTrigger(cronExpression));
        String key = UUIDUtil.getUUID();
        ScheduleTaskHolder scheduleTaskHolder = new ScheduleTaskHolder(task, schedule);
        map.put(key, scheduleTaskHolder);
        log.info("定时任务{}启动成功,key:{}", task.getName(), key);
        return key;
    }

    public void stopTask(String key) {
        if (StrUtil.isNotBlank(key)) {
            return;
        }
        ScheduleTaskHolder holder = map.get(key);
        if (ObjectUtil.isNull(holder)) {
            return;
        }
        boolean cancel = holder.getScheduledFuture().cancel(true);
        if (cancel) {
            log.info("定时任务{}停止成功,唯一标识{}", holder.getScheduleTask().getName(), key);
        } else {
            log.info("定时任务{}停止失败,唯一标识{}", holder.getScheduleTask().getName(), key);
        }
    }

    public String changeTask(String key, String cronExpression) {
        if (StringUtils.isAnyBlank(key, cronExpression)) {
            throw new FrameworkException("唯一标识或cron表达式不能为空");
        }
        ScheduleTaskHolder scheduleTaskHolder = map.get(key);
        if (ObjectUtil.isNull(scheduleTaskHolder)) {
            throw new FrameworkException("未找到对应的定时任务");
        }
        stopTask(key);
        return startTask(scheduleTaskHolder.getScheduleTask(), cronExpression);
    }

}
