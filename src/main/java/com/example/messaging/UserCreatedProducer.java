package com.example.messaging;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class UserCreatedProducer {

    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String QUEUE_NAME = "UserCreatedQueue";

    public void sendUserCreatedEvent(Long userId, String email) {
        try {
            ConnectionFactory factory =
                    (ConnectionFactory) new ActiveMQConnectionFactory(BROKER_URL);

            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Queue queue = session.createQueue(QUEUE_NAME);
            MessageProducer producer = session.createProducer(queue);

            MapMessage message = session.createMapMessage();
            message.setLong("userId", userId);
            message.setString("email", email);
            message.setLong("timestamp", System.currentTimeMillis());

            producer.send(message);

            System.out.println("📩 JMS message sent for user " + userId);

            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
