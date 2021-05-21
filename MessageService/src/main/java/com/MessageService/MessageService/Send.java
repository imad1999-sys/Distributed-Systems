package com.MessageService.MessageService;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Profile("send")
@RestController
public class Send {
    static Logger logger
            = LoggerFactory.getLogger(Send.class);

    private final static String QUEUE_NAME = "messages";

    public static Object Send(String m) throws  IOException, TimeoutException {
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

    public static void main(String[] argv) throws IOException, TimeoutException {


    }
}
