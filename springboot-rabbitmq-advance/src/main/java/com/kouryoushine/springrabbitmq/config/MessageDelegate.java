package com.kouryoushine.springrabbitmq.config;

import com.kouryoushine.springrabbitmq.bean.Order;
import com.kouryoushine.springrabbitmq.bean.Packaged;

import java.io.File;
import java.util.Map;

public class MessageDelegate {

    //handleMessage是默认名字，消息会自动路由到这个方法

    public void  handleMessage(byte[] messageBody){
        System.out.println("默认方法，消息内容："+ new String(messageBody));
    }


//    //经过适配器中的消息转换器
//    public void  customerMesthod(String messageBody){
//        System.out.println("字符串方法，消息内容："+ new String(messageBody));
//    }

    public void consumeMessage(Map json){
        System.out.println("JSON格式转换"+json.toString());

    }

    public void consumeMessage(Order order) {
        System.err.println("order对象, 消息内容, id: " + order.getId() +
                ", name: " + order.getName() +
                ", content: "+ order.getContent());
    }

    public void consumeMessage(Packaged pack) {
        System.err.println("package对象, 消息内容, id: " + pack.getId() +
                ", name: " + pack.getName() +
                ", content: "+ pack.getDescription());
    }

    public void consumeMessage(File file) {
        System.err.println("文件对象 方法, 消息内容:" + file.getName());
    }
}
