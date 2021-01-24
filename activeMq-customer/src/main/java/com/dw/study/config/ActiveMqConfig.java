package com.dw.study.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

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

    @Value("${spring.activemq.broker-url}")
    private  String brokerUrl;

    /**
     * 点对点（queue）定义存放消息的队列,队列名字MyActiveMQQueue
     * @return
     */
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("MyActiveMQQueue");
    }


    /**
     * 一对多（topic）定义存放消息的队列,队列名字MyActiveMQTopic
     */
    @Bean
    public ActiveMQTopic activeMQTopic() {
        return  new ActiveMQTopic("MyActiveMQTopic");
    }



    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(brokerUrl);
    }

    /**
     * 在yml中可以配置spring.jms.pub-sub-domain: false来确定使用哪种消息模式（点对点 或者 Topic），但是只能满足一种
     * 这里我们使用配置文件配置可满足 点对点 和 Topic
     * @param connectionFactory
     * @return
     */
    //Queue模式连接注入
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    //Topic模式连接注入
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        //设置为发布订阅方式, 默认情况下使用的生产消费者方式
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }


}
