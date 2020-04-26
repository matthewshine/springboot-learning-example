package com.kouryoushine.rabbitlearning.confirmListener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class Producer {
    public static void main(String[] args) throws  Exception{

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.103.92.234");
        connectionFactory.setPort(5673);
        connectionFactory.setVirtualHost("/");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //指定消息确认模式
        channel.confirmSelect();

        String exchangeName ="test-confir-exchange";
        String routingkey ="confirm.save";
        //发送消息
        String msg = "Hello MQ,Send confirm msg";
        channel.basicPublish(exchangeName,routingkey,null,msg.getBytes());

        //添加确认监听
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                //成功时 delivery tag(消息唯一标签
                System.out.println("-------ack-----"+l);
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("------no-ack-----"+l);
            }
        });


    }
}
