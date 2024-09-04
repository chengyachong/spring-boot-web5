package com.cyc.amqp;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootAmqpApplicationTests {
   @Autowired
  RabbitTemplate rabbitTemplate;

   @Autowired
    AmqpAdmin amqpAdmin;

    /**
     * 1.单播（点对点）
     */
    @Test
    void contextLoads() {
        //Message需要自己构造一个定义一个消息
        //rabbitTemplate.send();
        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是一个消息");
        map.put("data", Arrays.asList("helloWord",123,true));
        rabbitTemplate.convertAndSend("exchanges.direct","cyc.news",map);
    }
    @Test
    void  recive(){
       Object o=  rabbitTemplate.receiveAndConvert("cyc.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }
    @Test
    public void createExchange(){
        //创建一个Exchange
     //  amqpAdmin.declareExchange(new DirectExchange("amqp.exchange"));
      //创建一个队列Queue
        //amqpAdmin.declareQueue(new Queue("amqpAdmin.queue",true));
      amqpAdmin.declareBinding(new Binding("amqpAdmin.queue",Binding.DestinationType.QUEUE,"amqp.exchange","amqp.haha",null));
        System.out.println("创建完成");
    }

}
