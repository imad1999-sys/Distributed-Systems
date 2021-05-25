package com.SendAnReceiveMassagesService.SendAnReceiveMassagesService.services;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@Profile("send")
public class SendService {
    static Logger logger = LoggerFactory.getLogger(SendService.class);

    public static Object sendMessage(String m, String QUEUE_NAME) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,
                false,
                false,
                false,
                null);
        String message = m;
        channel.basicPublish("",
                QUEUE_NAME,
                null,
                message.getBytes("UTF-8"));
        logger.debug("[!] Sent '" + message + "'");
        channel.close();
        connection.close();
        return "The message is sent";
    }
}
