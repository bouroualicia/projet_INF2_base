package com.example.messaging;

import javax.jms.*;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class AuditProducer {
    public void sendAuditMessage(String eventType, String entityName, String details) {
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            try (Connection connection = factory.createConnection()) {
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Queue queue = session.createQueue("AuditQueue");
                MessageProducer producer = session.createProducer(queue);

                MapMessage message = session.createMapMessage();
                message.setString("eventType", eventType);
                message.setString("entityName", entityName);
                message.setString("details", details);

                producer.send(message);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}