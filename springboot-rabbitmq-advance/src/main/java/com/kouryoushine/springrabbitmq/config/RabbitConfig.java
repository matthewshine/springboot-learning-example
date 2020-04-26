package com.kouryoushine.springrabbitmq.config;




import com.kouryoushine.springrabbitmq.converter.ImageMessageConverter;
import com.kouryoushine.springrabbitmq.converter.PDFMessageConverter;
import com.kouryoushine.springrabbitmq.converter.TextMessageConverter;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class RabbitConfig {


    @Autowired
    RabbitTemplate rabbitTemplate;

    @Bean
    public RabbitAdmin rabbitAdmin(RabbitTemplate rabbitTemplate){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
         rabbitAdmin.setAutoStartup(true); //伴随spring启动时加载
         return rabbitAdmin;
    }


    @Bean
    public TopicExchange exchange01(){
        return  new TopicExchange("topic001",true,false);
    }

    @Bean
    public Queue queue01(){
        return  new Queue("queue001",true);
    }

    @Bean
    public Binding binding01(){
        return BindingBuilder.bind(queue01()).to(exchange01()).with("spring.*");
    }

    @Bean
    public TopicExchange exchange02(){
        return  new TopicExchange("topic002",true,false);
    }


    @Bean
    public Queue queue02(){
        return  new Queue("queue002",true);
    }

    @Bean
    public Binding binding02(){
        return BindingBuilder.bind(queue02()).to(exchange02()).with("rabbit.*");
    }


    @Bean
    public Queue queue03(){
        return  new Queue("queue003",true);
    }

    //mq.*的消息会通过交换机1发送给队列3
    @Bean
    public Binding binding03(){
        return BindingBuilder.bind(queue03()).to(exchange01()).with("mq.*");
    }

    @Bean
    public Queue queue_image(){
        return  new Queue("image_queue",true);
    }


    @Bean
    public Queue queue_pdf(){
        return  new Queue("pdf_queue",true);
    }


    @Bean
    SimpleMessageListenerContainer messageListenerContainer(){
        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitTemplate.getConnectionFactory());
        //同时监控多个队列
        container.setQueues(queue01(),queue02(),queue03(),queue_image(),queue_pdf());
        container.setConcurrentConsumers(1); //消费者数量
        container.setMaxConcurrentConsumers(10);
        container.setDefaultRequeueRejected(false);//是否重回队列
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);//自动签收
        container.setConsumerTagStrategy(new ConsumerTagStrategy() {
            //设置消费者名称的规则
            @Override
            public String createConsumerTag(String queue) {
                return queue+"_"+ UUID.randomUUID().toString();
            }
        });

//        container.setMessageListener(new ChannelAwareMessageListener() {
//                //有消息时就会通过下面方法获取
//            @Override
//            public void onMessage(Message message, Channel channel) throws Exception {
//                String string = new String(message.getBody());
//                System.out.println("--------消费者--------");
//                System.out.println(string);
//            }
//        });

//        //传入自定义适配器
//        MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
//        adapter.setDefaultListenerMethod("customerMesthod");
//        adapter.setMessageConverter(new TextMessageConvert()); //消息转换器
//        container.setMessageListener(adapter);


        /**
         * 2 适配器方式: 我们的队列名称 和 方法名称 也可以进行一一的匹配
         *
         MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
         adapter.setMessageConverter(new TextMessageConverter());
         Map<String, String> queueOrTagToMethodName = new HashMap<>();
         queueOrTagToMethodName.put("queue001", "method1");
         queueOrTagToMethodName.put("queue002", "method2");
         adapter.setQueueOrTagToMethodName(queueOrTagToMethodName);
         container.setMessageListener(adapter);
         */

        // 1.1 支持json格式的转换器
//
//         MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
//         adapter.setDefaultListenerMethod("consumeMessage");
//
//         Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
//         adapter.setMessageConverter(jackson2JsonMessageConverter);
//
//         container.setMessageListener(adapter);




        // 1.2 DefaultJackson2JavaTypeMapper & Jackson2JsonMessageConverter 支持java对象转换

//         MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
//         adapter.setDefaultListenerMethod("consumeMessage");
//
//         Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
//
//         DefaultJackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();
//        javaTypeMapper.setTrustedPackages("*");
//         jackson2JsonMessageConverter.setJavaTypeMapper(javaTypeMapper);
//
//         adapter.setMessageConverter(jackson2JsonMessageConverter);
//         container.setMessageListener(adapter);



        //1.3 DefaultJackson2JavaTypeMapper & Jackson2JsonMessageConverter 支持java对象多映射转换

         MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
         adapter.setDefaultListenerMethod("consumeMessage");
         Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
         DefaultJackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();
        javaTypeMapper.setTrustedPackages("*");
         Map<String, Class<?>> idClassMapping = new HashMap<String, Class<?>>();
         idClassMapping.put("order", com.kouryoushine.springrabbitmq.bean.Order.class);
         idClassMapping.put("packaged", com.kouryoushine.springrabbitmq.bean.Packaged.class);

         javaTypeMapper.setIdClassMapping(idClassMapping);

         jackson2JsonMessageConverter.setJavaTypeMapper(javaTypeMapper);
         adapter.setMessageConverter(jackson2JsonMessageConverter);
         container.setMessageListener(adapter);

        //1.4 ext convert
//
//        MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
//        adapter.setDefaultListenerMethod("consumeMessage");
//
//        //全局的转换器:
//        ContentTypeDelegatingMessageConverter convert = new ContentTypeDelegatingMessageConverter();
//
//        TextMessageConverter textConvert = new TextMessageConverter();
//        convert.addDelegate("text", textConvert);
//        convert.addDelegate("html/text", textConvert);
//        convert.addDelegate("xml/text", textConvert);
//        convert.addDelegate("text/plain", textConvert);
//
//        Jackson2JsonMessageConverter jsonConvert = new Jackson2JsonMessageConverter();
//        convert.addDelegate("json", jsonConvert);
//        convert.addDelegate("application/json", jsonConvert);
//
//        ImageMessageConverter imageConverter = new ImageMessageConverter();
//        convert.addDelegate("image/png", imageConverter);
//        convert.addDelegate("image", imageConverter);
//
//        PDFMessageConverter pdfConverter = new PDFMessageConverter();
//        convert.addDelegate("application/pdf", pdfConverter);
//
//
//        adapter.setMessageConverter(convert);
//        container.setMessageListener(adapter);


        return container;
    }


}
