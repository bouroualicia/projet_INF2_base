package com.example;

import java.net.URI;
 
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.example.messaging.AuditListener;
import com.example.messaging.UserCreatedListener;

public class Main {

    public static final String BASE_URI = "http://localhost:8080/api/";

    public static void main(String[] args) throws Exception {
        ResourceConfig config = new ResourceConfig()
                .packages("com.example.api")
                .register(JacksonFeature.class);

        GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI),
                config
        );

        System.out.println("\n🚀 API server running on " + BASE_URI);

        new Thread(new UserCreatedListener()).start();
        new Thread(new AuditListener()).start(); 
        
        Thread.currentThread().join();
    }
}