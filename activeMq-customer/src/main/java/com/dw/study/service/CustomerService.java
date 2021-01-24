package com.dw.study.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @Author dw
 * @ClassName CustomerService
 * @Description 消费者接受队列中的消息以 点对点 和 Topic为例
 * @Date 2021/1/23 23:23
 * @Version 1.0
 */
@Component
public class CustomerService {

    //############【如下配置了两个点对点消费者测试发现每个点对点消费者只能消费一次，如果多个点对点消费者，那么会平均接受到消息】########################

    /**
     * 【点对点消费者】
     * 使用JmsListener配置消费者监听名为MyActiveMQQueue的队列，其中message是接收到的消息
     * @param message
     * @return
     */
//    @JmsListener(destination = "MyActiveMQQueue", containerFactory = "jmsListenerContainerQueue")
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.实现双向绑定，在消费者接收到信息后给生产者返回一个内容，告诉生产者已经接收到消息
//    @SendTo("outQueue")
    public String handleQueueMessage(String message) {
        System.out.println("方法一成功接受message" + message);
        return "方法一成功接受message" + message;
    }

    /**
     * 【点对点消费者2】
     * 使用JmsListener配置消费者监听名为MyActiveMQQueue的队列，其中message是接收到的消息
     * @param message
     * @return
     */
//    @JmsListener(destination = "MyActiveMQQueue", containerFactory = "jmsListenerContainerQueue")
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.实现双向绑定，在消费者接收到信息后给生产者返回一个内容，告诉生产者已经接收到消息
//    @SendTo("outQueue")
    public String handleQueueMessage2(String message) {
        System.out.println("方法2成功接受message==" + message);
        return "方法2成功接受message==" + message;
    }


    /**
     * 【Topic消费者1】
     * 使用JmsListener配置消费者监听名为MyActiveMQTopic的队列，其中message是接收到的消息
     * @param message
     * @return
     */
    @JmsListener(destination = "MyActiveMQTopic", containerFactory = "jmsListenerContainerTopic")
    public void handleTopicMessage(String message) {
        System.out.println("成功接受message==" + message);
    }

    /**
     * 【Topic消费者2】
     * 使用JmsListener配置消费者监听名为MyActiveMQTopic的队列，其中message是接收到的消息
     * @param message
     * @return
     */
    @JmsListener(destination = "MyActiveMQTopic", containerFactory = "jmsListenerContainerTopic")
    public void handleTopicMessage2(String message) {
        System.out.println("成功接受message==" + message);
    }


}
