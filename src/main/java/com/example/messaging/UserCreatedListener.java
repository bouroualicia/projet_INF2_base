package com.example.messaging;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class UserCreatedListener implements Runnable {

    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String QUEUE_NAME = "UserCreatedQueue";

    @Override
    public void run() {
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
            Connection connection = factory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(QUEUE_NAME);
            MessageConsumer consumer = session.createConsumer(queue);

            System.out.println("👂 JMS Listener started...");

            while (true) {
                Message msg = consumer.receive();

                if (msg instanceof MapMessage) {
                    MapMessage map = (MapMessage) msg;

                    Long userId = map.getLong("userId");
                    String email = map.getString("email");
                    Long timestamp = map.getLong("timestamp");

                    System.out.println("📥 UserCreated event received:");
                    System.out.println("   userId = " + userId);
                    System.out.println("   email = " + email);
                    System.out.println("   timestamp = " + timestamp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
