package com.kouryoushine.rabbitlearning.dlx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

public class Consumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.103.92.234");
        connectionFactory.setPort(5673);
        connectionFactory.setVirtualHost("/");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        String exchangeName ="test-dlx-exchange";
        String routingkey ="dlx.#";
        String queueName ="test-dlx-queue";

        Map<String,Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange","dlx.exchange");//指明当前队列的DLX的名字 注：dlx.exchange本身只是个普通交换机
        channel.exchangeDeclare(exchangeName,"topic",true);
        //将参数添加到队列中
        channel.queueDeclare(queueName,true,false,false,arguments);
        channel.queueBind(queueName,exchangeName,routingkey);

        //声明死信队列（本身只是个普通的交换机和队列），区别在于其他exchange指定当前交换机为DLX
        channel.exchangeDeclare("dlx.exchange","topic",true,false,null);
        channel.queueDeclare("dlx.queue",true,false,false,null);
        channel.queueBind("dlx.queue","dlx.exchange","#");

        //创建消费者
        channel.basicConsume(queueName,true,new MyConsumer(channel));
    }
}
