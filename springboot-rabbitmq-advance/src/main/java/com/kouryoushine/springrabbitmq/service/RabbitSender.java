package com.kouryoushine.springrabbitmq.service;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    //对于有路由条目的消息ack结果判断,如果队列签收成功则ack返回true。用于解决消息可靠性投递
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String s) {
            System.out.println("correlationData:"+correlationData);
            System.out.println("ack:"+ack);
            System.out.println("s:"+s);
            if(ack){
                System.out.println("update database");
            }else {
                System.out.println("异常处理 ");
            }
        }
    };

    //对于不存在路由条目的消息，可能存在路由不成功的场景可以通过重回队列方式处理。

    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message, int i, String s, String s1, String s2) {
            System.out.println("returnText："+s);
            System.out.println("exchange："+s1);
            System.out.println("routingkey："+s2);
        }
    };
    public void send(Object message, Map<String,Object> properties) throws  Exception{
        MessageHeaders mds = new MessageHeaders(properties);

        Message message1 = MessageBuilder.createMessage(message,mds);
        rabbitTemplate.setConfirmCallback(confirmCallback); //手动确认
        rabbitTemplate.setReturnCallback(returnCallback); //重返队列
        CorrelationData collationData = new CorrelationData(); //设置消息唯一id,ack和补偿策略是用来识别唯一消息 注：必须全剧唯一
        collationData.setId("1233212");

        rabbitTemplate.convertAndSend("exchange-1","springboot.hello",message1, collationData);
    }

}
