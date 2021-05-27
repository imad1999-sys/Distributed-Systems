package com.SendAnReceiveMassagesService.SendAnReceiveMassagesService.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@Profile("receive")
public class ReceiveService {
    static Logger logger = LoggerFactory.getLogger(ReceiveService.class);
    static String message;

    private static String QUEUE_NAME ;
    public static Object receive(String QUEUE_NAME) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,
                false, false,false, null);

        logger.info("[!] Waiting for messages. To exit press Ctrl+C");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException {
                message = new String(body, "UTF-8");
                logger.info("[x] Message Recieved ' " + message + "' from "+QUEUE_NAME);
            }
        };
        String cons = channel.basicConsume(QUEUE_NAME, true, consumer);
        return "";
    }
}
