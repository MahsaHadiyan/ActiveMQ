package org.example;

import org.apache.qpid.jms.JmsConnectionFactory;

import javax.jms.*;
import java.io.Console;

/**
 * Created by Mahsa.Hadiyan  Date: 1/1/2024   Time: 12:07 PM
 **/
public class Consumer {
    public static void main(String[] args) throws Exception {
        // Create a connection to ActiveMQ JMS broker using AMQP protocol
        JmsConnectionFactory factory = new JmsConnectionFactory("amqp://localhost:5672");
        Connection connection = factory.createConnection("admin", "password");
        connection.start();

        // Create a session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create a queue
        Destination destination = session.createQueue("MyFirstQueue");

        // Create a consumer specific to queue
        MessageConsumer consumer = session.createConsumer(destination);

        Console c = System.console();
        String response;
        do {
            // Receive the message
            Message msg = consumer.receive();
            response = ((TextMessage) msg).getText();

            System.out.println("Received = " + response);

        } while (!response.equalsIgnoreCase("Quit"));

        // Close the connection
        connection.close();
    }
}
