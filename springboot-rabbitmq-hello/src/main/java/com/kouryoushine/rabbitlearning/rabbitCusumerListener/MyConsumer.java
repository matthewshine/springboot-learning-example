package com.kouryoushine.rabbitlearning.rabbitCusumerListener;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class MyConsumer extends DefaultConsumer {


    public MyConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

        System.out.println("-------myconsumer message--------------");
        System.out.println("consumerTag"+consumerTag);
        System.out.println("envelope"+envelope);
        System.out.println("properties"+properties);
        System.out.println("body"+new String(body));
//        super.handleDelivery(consumerTag, envelope, properties, body);
    }
}
