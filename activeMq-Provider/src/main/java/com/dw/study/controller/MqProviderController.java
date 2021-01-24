package com.dw.study.controller;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

/**
 * @Author dw
 * @ClassName MqProviderController
 * @Description
 * @Date 2021/1/23 23:18
 * @Version 1.0
 */
@RestController
@RequestMapping("myMq")
public class MqProviderController {

    //注入存放消息的点对点队列
    @Autowired
    private Queue queue;

    //注入存放消息的Topic队列
    @Autowired
    private ActiveMQTopic activeMQTopic;

    /**
     * 注入springBoot封装的工具类
     */
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 发送消息到点对点的消息队列中
     * @param message
     */
    @RequestMapping("sendToQueue")
    public void sendToQueue(String  message) {
        //方法一：添加消息到消息队列
        jmsMessagingTemplate.convertAndSend(queue, message);
        //方法二：这种方式不需要手动创建queue，系统会自行创建名为test的队列
        //jmsMessagingTemplate.convertAndSend("test", name);
    }

    /**
     * 发送消息到Topic的消息队列中
     * @param message
     */
    @RequestMapping("sendToTopic")
    public void sendToTopic(String  message) {
        jmsMessagingTemplate.convertAndSend(activeMQTopic, message);
    }

}
