package com.Yu.Pan.schedule;

public interface ScheduleTask extends Runnable {

    /**
     * 获取任务名称
     *
     * @return
     */
  String getName();
}
