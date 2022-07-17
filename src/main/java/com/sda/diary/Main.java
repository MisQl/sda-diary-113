package com.sda.diary;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        EntryRepository entryRepository = new EntryHibernateRepository(sessionFactory);
        EntryService entryService = new EntryService(entryRepository);
        ObjectMapper objectMapper = new ObjectMapper();
        EntryController entryController = new EntryController(entryService, objectMapper);
        UserInterface userInterface = new UserInterface(entryController);
        userInterface.run();
    }
}
