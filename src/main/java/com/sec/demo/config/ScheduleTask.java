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

    @Scheduled(cron = "*/1 * * * * *")
//    @Scheduled(fixedRate = 1000)
    public void produceMq(){

        count++;
        String messagecount = new Date().toLocaleString()+"    "+"您发送的第"+count+"条消息------------------------->>>>";
        //读取csv文件
        String srcPath = "C:\\Users\\03010347\\Desktop\\各类项目问题\\太仓项目\\齿轮箱低速轴_垂直径向_25600Hz_加速度_1200.5_20180716014519.csv";
        String value = CommonUtil.readCSV(srcPath);

//        Date date = CommonUtil.randomDate("2010-08-30", "2018-10-04");
        String key = "齿轮箱低速轴2_垂直径向_25600Hz_加速度_1200.5_"+ CommonUtil.timedel(new Date());
        String message = key + ":" + value;
        if(messageMq.asynSave(message)){
            logger.info(messagecount);
            logger.info(message);
        }
    }

}
