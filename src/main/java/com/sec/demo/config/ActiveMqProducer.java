package com.sec.demo.config;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class ActiveMqProducer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, String message){
        jmsMessagingTemplate.convertAndSend(destination,message);
    }

}
