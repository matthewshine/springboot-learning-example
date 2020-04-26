package com.kouryoushine.rabbitlearning.dlx;

import com.rabbitmq.client.*;

import java.util.HashMap;
import java.util.Map;

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
        //可以为消息增加一些自定义属性
        Map<String,Object> headers = new HashMap<>();
        headers.put("hd1","11");
        headers.put("hd2","22");
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)  //投递模式
                .contentEncoding("UTF-8")
                .expiration("10000") //10s不消费就删除
                .headers(headers)
                .build();
        String exchangeName ="test-dlx-exchange";
        String routingkey ="dlx.save";
        //发送消息
        String msg = "Hello MQ,Send dlx msg";
        //第三个属性mandatoryt=ture，接收不可达消息
        channel.basicPublish(exchangeName,routingkey,true,properties,msg.getBytes());


    }
}
