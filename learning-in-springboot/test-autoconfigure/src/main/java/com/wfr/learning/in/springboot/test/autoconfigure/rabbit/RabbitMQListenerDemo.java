package com.wfr.learning.in.springboot.test.autoconfigure.rabbit;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 基于 {@link org.springframework.amqp.rabbit.annotation.RabbitListener} 示例
 *
 * @author wangfarui
 * @since 2022/4/2
 */
@Service
public class RabbitMQListenerDemo {

    @RabbitListener(queues = {RabbitParamConstants.ORDER_ONE})
    public void listenRabbitMessage(String message, @Headers Map<String, Object> headMap) {
        System.out.println("消息打印: " + message);
//        for (Map.Entry<String, Object> entry : headMap.entrySet()) {
//            System.out.println(entry.getKey() + " - " + entry.getValue());
//        }
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(RabbitParamConstants.ORDER_ONE),
            exchange = @Exchange(name = RabbitParamConstants.EXCHANGE_NAME)))
    public void listenRabbitMessageByExchange(String message, @Headers Map<String, Object> headMap) {
        System.out.println("Exchange交换机消息打印: " + message);
        for (Map.Entry<String, Object> entry : headMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
