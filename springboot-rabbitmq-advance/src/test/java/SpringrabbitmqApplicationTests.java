import com.fasterxml.jackson.databind.ObjectMapper;
import com.kouryoushine.springrabbitmq.bean.Order;
import com.kouryoushine.springrabbitmq.bean.Packaged;
import com.kouryoushine.springrabbitmq.service.RabbitSender;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.QueueInformation;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringrabbitmqApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    RabbitAdmin rabbitAdmin;

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Test
    void test1(){
        String beanName = rabbitAdmin.getBeanName();
        QueueInformation queue001 = rabbitAdmin.getQueueInfo("queue001");
        System.out.println(queue001.getMessageCount());
    }

    @Test
    void test2(){
        //消息自定义参数
        MessageProperties messageProperties = new MessageProperties();
        Map<String, Object> headers = messageProperties.getHeaders();
        headers.put("desc","this is a test message");
        headers.put("type","tempalte-msg");

        Message message = new Message("HELLO MA".getBytes(),messageProperties);
        rabbitTemplate.convertAndSend("topic001", "spring.amqp", message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                System.out.println("添加额外的设置");
                message.getMessageProperties().getHeaders().put("desc2","额外新加的属性");
                return message;
            }
        });

    }



    @Test
    void test3(){
        //消息自定义参数
        rabbitTemplate.convertAndSend("topic002", "rabbit.amqp", "Hello String msg");

    }


    @Test
    public void testSendJsonMessage() throws Exception {

        Order order = new Order();
        order.setId("001");
        order.setName("消息订单");
        order.setContent("描述信息");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(order);
        System.err.println("order 4 json: " + json);

        MessageProperties messageProperties = new MessageProperties();
        //这里注意一定要修改contentType为 application/json
        messageProperties.setContentType("application/json");
        Message message = new Message(json.getBytes(), messageProperties);

        rabbitTemplate.send("topic001", "spring.order", message);
    }


    @Test
    public void testSendJavaMessage() throws Exception {

        Order order = new Order();
        order.setId("001");
        order.setName("订单消息");
        order.setContent("订单描述信息");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(order);
        System.err.println("order 4 json: " + json);

        MessageProperties messageProperties = new MessageProperties();
        //这里注意一定要修改contentType为 application/json
        messageProperties.setContentType("application/json");
        messageProperties.getHeaders().put("__TypeId__", "com.kouryoushine.springrabbitmq.bean.Order");
        Message message = new Message(json.getBytes(), messageProperties);

        rabbitTemplate.send("topic001", "spring.order", message);
    }

    @Test
    public void testSendMappingMessage() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        Order order = new Order();
        order.setId("001");
        order.setName("订单消息");
        order.setContent("订单描述信息");

        String json1 = mapper.writeValueAsString(order);
        System.err.println("order 4 json: " + json1);

        MessageProperties messageProperties1 = new MessageProperties();
        //这里注意一定要修改contentType为 application/json
        messageProperties1.setContentType("application/json");
        messageProperties1.getHeaders().put("__TypeId__", "com.kouryoushine.springrabbitmq.bean.Order");
        Message message1 = new Message(json1.getBytes(), messageProperties1);
        rabbitTemplate.send("topic001", "spring.order", message1);

        Packaged pack = new Packaged();
        pack.setId("002");
        pack.setName("包裹消息");
        pack.setDescription("包裹描述信息");

        String json2 = mapper.writeValueAsString(pack);
        System.err.println("pack 4 json: " + json2);

        MessageProperties messageProperties2 = new MessageProperties();
        //这里注意一定要修改contentType为 application/json
        messageProperties2.setContentType("application/json");
        messageProperties2.getHeaders().put("__TypeId__", "com.kouryoushine.springrabbitmq.bean.Packaged");
        Message message2 = new Message(json2.getBytes(), messageProperties2);
        rabbitTemplate.send("topic001", "spring.pack", message2);
    }

    @Autowired
    RabbitSender rabbitSender;
    @Test
    void test33 () throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("number",12344);
        map.put("send_time",new Date());
        rabbitSender.send("hello springboot msg11",map);
    }


}
