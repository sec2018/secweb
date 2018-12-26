package com.sec.demo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Configurable
@EnableScheduling
public class ScheduleTask {

    private static int count = 0;

    private static final Logger logger = LogManager.getLogger(ScheduleTask.class);

    @Resource
    private MessageMq messageMq;

//    @Scheduled(cron = "*/1 * * * * *")
//    @Scheduled(fixedRate = 50)
    public void produceMq(){
        count++;
        String message = new Date().toLocaleString()+"    "+"您发送的第"+count+"条消息------------------------->>>>";
        if(messageMq.asynSave(message)){
            logger.info(message);
        }
    }

}
