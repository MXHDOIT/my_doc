package com.frank;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author: maxinhang.
 */
public class QuartzTest {
    public static void main(String[] args) {
        try {
            // 从 Factory 中获取 Scheduler 实例
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // 开始并关闭
            scheduler.start();

            // 定义一个 Job（用JobDetail描述的Job），并将这个 Job 绑定到我们写的 HelloJob 这个任务类上
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("job1", "group1") // 名字为 job1，组为 group1
                    .build();

            // 现在触发任务，让任务执行，然后每5秒重复执行一次
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(5)
                            .repeatForever())
                    .build();

            // 告知 Quartz 使用我们的 Trigger 去调度这个 Job
            scheduler.scheduleJob(job, trigger);


            // 为了在 shutdown 之前让 Job 有足够的时间被调度执行，所以这里当前线程睡眠30秒
            Thread.sleep(30000);

//            scheduler.shutdown();

        } catch (SchedulerException | InterruptedException se) {
            se.printStackTrace();
        }
    }
}
