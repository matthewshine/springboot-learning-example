package com.kouryoushine.rabbitlearning.limit;

import com.rabbitmq.client.*;

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

        String exchangeName ="test-qos-exchange";
        String routingkey ="qos.save";
        //发送消息
        String msg = "Hello MQ,Send QOS msg";
        //第三个属性mandatoryt=ture，接收不可达消息

        for(int i=0;i<5;i++){
            channel.basicPublish(exchangeName,routingkey,true,null,msg.getBytes());
        }



    }
}
