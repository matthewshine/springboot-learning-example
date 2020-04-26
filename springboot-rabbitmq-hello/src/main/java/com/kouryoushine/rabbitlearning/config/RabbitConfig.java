package com.kouryoushine.rabbitlearning.config;//package com.kouryoushine.rabbitlearning.config;
//
//
//
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitConfig {
//
//    @Bean
//    public ConnectionFactory connectionFactory(){
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setAddresses("47.103.92.234");
//        connectionFactory.setPort(5673);
//        connectionFactory.setUsername("guest");
//        connectionFactory.setPassword("guest");
//        connectionFactory.setVirtualHost("/");
//        return  connectionFactory;
//    }
//
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
//        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
//         rabbitAdmin.setAutoStartup(true); //伴随spring启动时加载
//         return rabbitAdmin;
//    }
//
//}
