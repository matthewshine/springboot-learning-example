package com.kouryoushine.rabbitlearning.start;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
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

        //通过channel发送消息
        for(int i =0;i<5;i++){

            String msg ="hello MQ";
            //注：第一个exchage为空就会启用默认交换机，直接用routingkey匹配queue
            channel.basicPublish("","test01",null,msg.getBytes());
        }

        //重要：关闭连接
        channel.close();
        connection.close();
    }
}
