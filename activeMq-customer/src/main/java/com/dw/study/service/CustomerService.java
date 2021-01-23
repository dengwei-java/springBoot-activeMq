package com.dw.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @Author dw
 * @ClassName CustomerService
 * @Description
 * @Date 2021/1/23 23:23
 * @Version 1.0
 */
@Component
public class CustomerService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    // 使用JmsListener配置消费者监听名为MyActiveMQQueue的队列，其中message是接收到的消息
    @JmsListener(destination = "MyActiveMQQueue")
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo("SQueue")
    public String handleMessage(String message) {
        System.out.println("成功接受message" + message);
        return "成功接受message" + message;
    }


}
