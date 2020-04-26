package com.kouryoushine.rabbitlearning.rabbitCusumerListener;

import com.rabbitmq.client.*;

import java.io.IOException;

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

        String exchangeName ="test-consumer-exchange";
        String routingkey ="consumer.save";
        //发送消息
        String msg = "Hello MQ,Send consumer msg";
        //第三个属性mandatoryt=ture，接收不可达消息
        channel.basicPublish(exchangeName,routingkey,true,null,msg.getBytes());

        //添加确认监听,如果消息不可达，就会在这里接收
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println("-------return msg--------");
                System.err.println(i+s+s1+s2);
                System.out.println(new String(bytes));
            }
        });


    }
}
