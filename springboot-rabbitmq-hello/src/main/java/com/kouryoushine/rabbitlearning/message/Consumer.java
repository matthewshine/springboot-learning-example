package com.kouryoushine.rabbitlearning.message;

import com.rabbitmq.client.*;

public class Consumer {
    public static void main(String[] args) throws Exception {

        //获取连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.103.92.234");
        connectionFactory.setPort(5673);
        connectionFactory.setVirtualHost("/");


        //创建连接
        Connection connection = connectionFactory.newConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //声明一个队列
        channel.queueDeclare("test01",true,false,false,null);

        //创建消费者
        String queueName ="test01";
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName,true,queueingConsumer );

        //获取消息
        while(true){

            QueueingConsumer.Delivery  delivery = queueingConsumer.nextDelivery();

            AMQP.BasicProperties properties = delivery.getProperties();
            System.out.println("获取消息："+new String(delivery.getBody()));
            System.out.println("appid"+properties.getAppId());
            System.out.println("headers"+properties.getHeaders().toString());
        }



    }
}
