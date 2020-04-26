package com.kouryoushine.rabbitlearning.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送自定义属性的消息
 */
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


        //通过channel发送消息
        for(int i =0;i<5;i++){

            String msg ="hello MQ";
            //注：第一个exchage为空就会启用默认交换机，直接用routingkey匹配queue
            channel.basicPublish("","test01",properties,msg.getBytes());
        }

        //重要：关闭连接
        channel.close();
        connection.close();
    }
}
