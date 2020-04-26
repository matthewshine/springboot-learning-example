package com.kouryoushine.rabbitlearning.ack;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.103.92.234");
        connectionFactory.setPort(5673);
        connectionFactory.setVirtualHost("/");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //指定消息确认模式
        String exchangeName ="test-ack-exchange";
        String routingkey ="ack.#";
        String queueName ="test-ack-queue";

        channel.exchangeDeclare(exchangeName,"topic",true);
        channel.queueDeclare(queueName,true,false,false,null);
        channel.queueBind(queueName,exchangeName,routingkey);

        //创建消费者
        //手工签收，第二个参数autoack一定是false
        channel.basicConsume(queueName,false,new MyConsumer(channel));
    }
}
