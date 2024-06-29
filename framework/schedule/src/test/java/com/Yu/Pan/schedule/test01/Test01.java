package com.Yu.Pan.schedule.test01;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.thread.ThreadUtil;
import com.Yu.Pan.schedule.ScheduleManager;
import com.Yu.Pan.schedule.test01.config.ScheduleConfig;
import com.Yu.Pan.schedule.test01.test.SimpleScheduleTask;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能:
 * 作者:何宇
 * 日期：2024/6/29 10:14
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScheduleConfig.class)
public class Test01 {
    @Autowired
    ScheduleManager scheduleManager;
    @Autowired
    SimpleScheduleTask task;
    @org.junit.Test
    public void test() {
        String s = scheduleManager.startTask(task, "0/5 * * * * ?");
        ThreadUtil.sleep(10000);
        Assert.notBlank(s, "任务启动失败");
        System.out.println("=========================================");
        System.out.println("修改定时任务");
        ThreadUtil.sleep(10000);
        scheduleManager.changeTask(s, "0/1 * * * ?");
    }
}
