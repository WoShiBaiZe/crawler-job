package com.baize.job.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskTest {
//    @Scheduled(cron = "0/1 * * * * ?")
    public void test(){
        System.out.println("定时任务执行了");
    }
}
