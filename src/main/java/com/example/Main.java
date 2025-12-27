package com.example;
import com.example.domain.User;
import com.example.messaging.UserCreatedListener;
import com.example.persistence.Jpa;
import jakarta.persistence.EntityManager;


import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import java.net.URI;

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

        System.out.println("✅ User persisted in DB");
        Thread.currentThread().join();

    }
}
