package com.kouryoushine.rabbitlearning.ack;

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
        Integer num =(Integer) properties.getHeaders().get("num");
        System.out.println("body"+new String(body));
        if(0==num){
            //第三个参数设定是否重回队列
            channel.basicNack(envelope.getDeliveryTag(),false,true);
        }else {
            channel.basicAck(envelope.getDeliveryTag(),false);
        }


    }
}
