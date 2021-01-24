package com.dw.study.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.Queue;

import static org.apache.activemq.ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE;

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


    /**
     * ActiveMQ 连接工厂用于创建和中间件的连接
     * @return
     */
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
        // 手动签收
        bean.setSessionTransacted(false);
        /**
         *  设置消息的签收模式AUTO_ACKNOWLEDGE = 1    自动确认
         *   CLIENT_ACKNOWLEDGE = 2    客户端手动确认
         * 　DUPS_OK_ACKNOWLEDGE = 3    自动批量确认
         * 　SESSION_TRANSACTED = 0    事务提交并确认
         * 　INDIVIDUAL_ACKNOWLEDGE = 4  单条消息确认 activemq 独有       　　　　　　
         */
        bean.setSessionAcknowledgeMode(INDIVIDUAL_ACKNOWLEDGE);
        // 配置消息的重发规则
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy());
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    //Topic模式连接注入
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        //设置为发布订阅方式, 默认情况下使用的生产消费者方式
        bean.setPubSubDomain(true);
        // topic持久化
        bean.setSubscriptionDurable(true);
        bean.setClientId("defaultTopic");
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }


    /**
     *消息的重发规则配置
     */
    @Bean
    public RedeliveryPolicy redeliveryPolicy() {
        RedeliveryPolicy  redeliveryPolicy=   new RedeliveryPolicy();
        // 是否在每次尝试重新发送失败后,增长这个等待时间
        redeliveryPolicy.setUseExponentialBackOff(true);
        // 重发次数,默认为6次
        redeliveryPolicy.setMaximumRedeliveries(5);
        // 重发时间间隔,默认为1000ms（1秒）
        redeliveryPolicy.setInitialRedeliveryDelay(1000);
        // 重发时长递增的时间倍数2
        redeliveryPolicy.setBackOffMultiplier(2);
        // 是否避免消息碰撞
        redeliveryPolicy.setUseCollisionAvoidance(false);
        // 设置重发最大拖延时间-1表示无延迟限制
        redeliveryPolicy.setMaximumRedeliveryDelay(-1);
        return redeliveryPolicy;
    }

}
