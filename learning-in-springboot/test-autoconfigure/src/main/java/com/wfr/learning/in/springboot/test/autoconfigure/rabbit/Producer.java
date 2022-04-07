package com.wfr.learning.in.springboot.test.autoconfigure.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        //设置 RabbitMQ 地址
        factory.setHost("localhost");
        //建立到代理服务器到连接
        Connection conn = factory.newConnection();
        //获得信道
        Channel channel = conn.createChannel();
//        //声明交换器
//        String exchangeName = RabbitParamConstants.EXCHANGE_NAME;
//        channel.exchangeDeclare(exchangeName, "direct", true);
//
//        String routingKey = RabbitParamConstants.ORDER_ONE;
//        //发布消息
//        byte[] messageBodyBytes = "quit".getBytes();
//        channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);

        String queueName = RabbitParamConstants.ORDER_ONE;
        String exchangeName = RabbitParamConstants.EXCHANGE_NAME;
//        channel.queueDeclare(queueName, true, false, false, null);
        int n = 0;
        while (n++ < 10) {
            String message = "这是第" + n + "条消息";
            channel.basicPublish("", queueName, null, message.getBytes());
        }

        channel.close();
        conn.close();
    }
}