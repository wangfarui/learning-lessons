package com.wfr.learning.in.springboot.test.autoconfigure.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 基于 {@link org.springframework.amqp.rabbit.annotation.RabbitHandler} 示例
 *
 * @author wangfarui
 * @since 2022/4/2
 */
@Service
//@RabbitListener(queues = RabbitQueueConstants.ORDER_ONE)
public class RabbitMQHandlerDemo {

//    @RabbitHandler()
//    public void handlerRabbitMessage(String message) {
//        System.out.println("消息打印: " + message);
//    }
}
