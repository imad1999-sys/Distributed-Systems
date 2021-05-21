package com.MessageService.MessageService;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Profile("receive")
public class Receive {
    static Logger logger = LoggerFactory.getLogger(Receive.class);
    static String message;

    private final static String QUEUE_NAME = "messages";

    public static Object receive() throws IOException, TimeoutException{
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
                logger.info("[x] Message Recieved' " + message + "'");
            }
        };
        String cons = channel.basicConsume(QUEUE_NAME, true, consumer);
        return "The messeges is " + cons;
    }
    public  static void main(String[] argv)
            throws IOException, TimeoutException {
        receive();
    }
}