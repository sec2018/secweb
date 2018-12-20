package com.sec.demo.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class MessageMq {

    //队列模式  一个消息只有一个消费者消费
    private static Destination asyndestination = new ActiveMQQueue("ay.queue.asyn.save");

    //topic模式   广播形式
    private static Destination topicdestination = new ActiveMQTopic("topic01");

    @Resource
    private ActiveMqProducer activeMqProducer;

    public boolean asynSave(String message){
        try {
            activeMqProducer.sendMessage(asyndestination,message);
//            activeMqProducer.sendMessage(topicdestination,message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
