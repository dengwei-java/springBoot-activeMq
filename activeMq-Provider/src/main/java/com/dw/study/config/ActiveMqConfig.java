package com.dw.study.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * @Author dw
 * @ClassName ActiveMqConfig
 * @Description
 * @Date 2021/1/23 23:17
 * @Version 1.0
 */
@Configuration
public class ActiveMqConfig {


    /**
     * 定义存放消息的队列,队列名字MyActiveMQQueue
     * @return
     */
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("MyActiveMQQueue");
    }

}
