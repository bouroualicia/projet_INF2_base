package com.example.messaging;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import com.example.service.AuditService;

public class AuditListener implements Runnable {
    private final AuditService auditService = new AuditService();

    @Override
    public void run() {
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = factory.createConnection();
            connection.start();
            
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            Queue queue = session.createQueue("AuditQueue"); 
            MessageConsumer consumer = session.createConsumer(queue);

            System.out.println("👂 Audit Listener: Ready and listening for events...");

            while (true) {
                Message msg = consumer.receive();
                if (msg instanceof MapMessage) {
                    MapMessage map = (MapMessage) msg;
  
                    String type = map.getString("eventType");
                    String entity = map.getString("entityName");
                    String info = map.getString("details");

                    auditService.logEvent(type, entity, info);
                    
                    System.out.println("🔍 Audit Module: Logged [" + type + "] for " + entity);
                }
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }
}