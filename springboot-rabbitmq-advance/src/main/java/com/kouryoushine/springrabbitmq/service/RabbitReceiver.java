package com.kouryoushine.springrabbitmq.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class RabbitReceiver {

    /**
     * 如果没有exchagne,queue会自动创建
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1",durable = "true"),
            exchange = @Exchange(value = "exchange-1",durable = "true" ,type = "topic",ignoreDeclarationExceptions = "true"),
            key = "springboot.*"
    ))
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws IOException {
        System.out.println("消费端内容"+message.getPayload());
        Long deliverytag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        //手工签收
        channel.basicAck(deliverytag,false);
    }
}
