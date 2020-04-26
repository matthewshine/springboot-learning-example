package com.kouryoushine.rabbitlearning.limit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class MyConsumer extends DefaultConsumer {
    private  Channel channel;

    public MyConsumer(Channel channel) {
        super(channel);
        this.channel=channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

        System.out.println("-------myconsumer message--------------");
        System.out.println("consumerTag"+consumerTag);
        System.out.println("envelope"+envelope);
        System.out.println("properties"+properties);
        System.out.println("body"+new String(body));
        //主动确认消息，表示可以继续发送下一条了
        channel.basicAck(envelope.getDeliveryTag(),false);
        //注：如果注释掉，意味着消费者不确认。生产者限流就不会继续发送消息，消息队列产生3条unack的消息
    }
}
