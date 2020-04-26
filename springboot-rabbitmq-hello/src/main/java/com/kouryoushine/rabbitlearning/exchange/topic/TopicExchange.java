package com.kouryoushine.rabbitlearning.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TopicExchange {

    public static void main(String[] args)  throws  Exception{

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.103.92.234");
        connectionFactory.setPort(5673);
        connectionFactory.setVirtualHost("/");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        String exchangeName ="test-topic-exchange";
        String  routingkey1 ="user.save"; //主要区别在这里
        String  routingkey2 ="user.update"; //主要区别在这里
        String  routingkey3 ="user.delete.dao"; //主要区别在这里
        String msg = " direct exchange message";

        channel.basicPublish(exchangeName,routingkey1,null,(msg+routingkey1).getBytes());
        channel.basicPublish(exchangeName,routingkey2,null,(msg+routingkey2).getBytes());
        channel.basicPublish(exchangeName,routingkey3,null,(msg+routingkey3).getBytes());

        channel.close();
        connection.close();


    }
}
