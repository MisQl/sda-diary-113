package com.sda.diary;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class EntryHibernateRepository implements EntryRepository {

    private final SessionFactory sessionFactory;

    public EntryHibernateRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Entry save(Entry entry) {
        Session session = sessionFactory.openSession();
        try (session) {
            Transaction transaction = session.beginTransaction();
            session.persist(entry);
            transaction.commit();
            return entry;
        } catch (Exception e) {
            Transaction transaction = session.getTransaction();
            transaction.rollback();
            throw new RuntimeException("Operacja na bazie danych nie powiodła się");
        }
    }
}
