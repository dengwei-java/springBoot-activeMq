package com.dw.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @Author dw
 * @ClassName CustomerMainStater
 * @Description
 * @Date 2021/1/23 23:08
 * @Version 1.0
 */
@SpringBootApplication
@EnableJms // //启动消息队列
public class CustomerMainStater {
    public static void main(String[] args) {
        SpringApplication.run(CustomerMainStater.class, args);
    }
}
