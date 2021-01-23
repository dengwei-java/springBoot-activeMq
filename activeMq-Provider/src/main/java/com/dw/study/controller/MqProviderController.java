package com.dw.study.controller;

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

    //注入存放消息的队列，用于下列方法一
    @Autowired
    private Queue queue;

    /**
     * 注入springBoot封装的工具类
     */
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("send")
    public void send(String  message) {
        //方法一：添加消息到消息队列
        jmsMessagingTemplate.convertAndSend(queue, message);
        //方法二：这种方式不需要手动创建queue，系统会自行创建名为test的队列
        //jmsMessagingTemplate.convertAndSend("test", name);
    }



}
