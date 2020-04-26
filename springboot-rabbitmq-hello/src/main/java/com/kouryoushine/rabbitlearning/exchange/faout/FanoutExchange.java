package com.kouryoushine.rabbitlearning.exchange.faout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class FanoutExchange {

    public static void main(String[] args)  throws  Exception{

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.103.92.234");
        connectionFactory.setPort(5673);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        String exchangeName ="test-fanout-exchange";
        String  routingkey =""; //主要区别在这里
        String msg = " fanout exchange message";

        channel.basicPublish(exchangeName,routingkey,null,msg.getBytes());

        channel.close();
        connection.close();


    }
}
