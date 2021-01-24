package com.dw.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @Author dw
 * @ClassName ProviderMainStater
 * @Description
 * @Date 2021/1/23 23:06
 * @Version 1.0
 */
@SpringBootApplication
@EnableJms //启用消息队列
public class ProviderMainStater {

    public static void main(String[] args) {
        SpringApplication.run(ProviderMainStater.class, args);
    }

}
