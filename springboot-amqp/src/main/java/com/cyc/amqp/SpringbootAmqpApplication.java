package com.cyc.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置类
 *1.RabbitAutoConfiguation
 * 2.有自动配置了连接工厂ConnectionFactory
 * 3.RabbitProperties 封装了 配置
 * 4.rabbitTemplete:给rabbitMq发送和接受消息
 * 5。amqpAdmin：RabbitMq系统管理功能组件
 *     AmqpAdmin:创建和删除Queue
 */
@EnableRabbit //开启基于注解rabbitMQ模式 + @rabbitLister 实现rabbit监听
@SpringBootApplication
public class SpringbootAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAmqpApplication.class, args);
    }

}
