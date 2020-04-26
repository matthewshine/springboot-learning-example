package com.kouryoushine.springrabbitmq.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

public class TextMessageConvert implements MessageConverter {

    //java对象转换为message对象
    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {

        //字符串转消息类型
        return new Message(o.toString().getBytes(),messageProperties);
    }

    //Message对象转换为java对象
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        boolean flag = message.getMessageProperties().getHeaders().containsKey("text");

        if(flag){
            //如果消息属性中含有text则直接返回消息的字符串类型
            return  new String(message.getBody());
        }

         return  new String(message.getBody())+"没有经过转换消息";
    }
}
