package com.dw.study.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @Author dw
 * @ClassName ManualSignService
 * @Description 手动签收（确认已处理的消息）模式、以及死信处理
 * @Date 2021/1/24 16:10
 * @Version 1.0
 */
@Service
@Slf4j
public class ManualSignService {


    /**
     * 测试不签收的情况（创建死信）
     * @param message
     * @param session
     */
    @JmsListener(destination = "MyActiveMQQueue", containerFactory = "jmsListenerContainerQueue")
    public void receiveQueueMessage(ActiveMQMessage message, Session session) {
        int i = 0;
        try {
            System.out.println("收到消息" + message +":"+ i++);
            // 消息签收
            // message.acknowledge();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        try {
            // 消息重发
            session.recover();
        } catch (JMSException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 处理死信
     */
    @JmsListener(destination = "DLQ.MyActiveMQQueue", containerFactory = "jmsListenerContainerQueue")
    public void receiveDLQDefault(ActiveMQMessage message) {
        System.out.println("处理死信" + message);
        try {
            message.acknowledge();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}
