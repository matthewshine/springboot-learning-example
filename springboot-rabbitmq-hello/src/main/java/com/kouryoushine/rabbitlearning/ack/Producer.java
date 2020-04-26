package com.kouryoushine.rabbitlearning.ack;

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

        String exchangeName ="test-ack-exchange";
        String routingkey ="ack.save";
        //发送消息

        Map<String,Object> headers = new HashMap<>();


        for(int i=0;i<5;i++){
            String msg = "Hello MQ,Send ACK msg"+i;
            headers.put("num",i);
            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder()
                    .deliveryMode(2)
                    .contentEncoding("UTF-8")
                    .headers(headers)
                    .build();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            channel.basicPublish(exchangeName,routingkey,true,basicProperties,msg.getBytes());
        }



    }
}
