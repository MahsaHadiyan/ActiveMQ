package org.example;

import org.apache.qpid.jms.JmsConnectionFactory;

import javax.jms.*;
import java.util.Scanner;

/**
 * Created by Mahsa.Hadiyan  Date: 1/1/2024   Time: 12:04 PM
 **/
public class Producer {

    public static void main(String[] args) throws Exception {

        // Create a connection to ActiveMQ JMS broker using AMQP protocol
        JmsConnectionFactory factory = new JmsConnectionFactory("amqp://localhost:5672");
        Connection connection = factory.createConnection("admin", "password");
        connection.start();

        // Create a session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create a queue
        Destination destination = session.createQueue("MyFirstQueue");

        // Create a producer specific to queue
        MessageProducer producer = session.createProducer(destination);

        Scanner input = new Scanner(System.in);
        String response;
        do {
            System.out.println("Enter message: ");
            response = input.nextLine();
            // Create a message object
            TextMessage msg = session.createTextMessage(response);

            // Send the message to the queue
            producer.send(msg);

        } while (!response.equalsIgnoreCase("Quit"));
        input.close();

        // Close the connection
        connection.close();
    }
}
