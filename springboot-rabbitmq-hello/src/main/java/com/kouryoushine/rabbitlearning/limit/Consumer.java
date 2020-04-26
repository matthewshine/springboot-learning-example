package com.kouryoushine.rabbitlearning.limit;

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
        String exchangeName ="test-qos-exchange";
        String routingkey ="qos.#";
        String queueName ="test-qos-queue";

        channel.exchangeDeclare(exchangeName,"topic",true);
        channel.queueDeclare(queueName,true,false,false,null);
        channel.queueBind(queueName,exchangeName,routingkey);

        //创建消费者
        //如果限流，第二个参数autoack一定是false
        channel.basicQos(0,3,false); //每次只发送3条消息，收到ack应答后再继续发送
        channel.basicConsume(queueName,false,new MyConsumer(channel));
    }
}
