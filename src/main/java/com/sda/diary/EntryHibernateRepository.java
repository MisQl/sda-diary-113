package com.sda.diary;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@RequiredArgsConstructor
public class EntryHibernateRepository implements EntryRepository {

    private final SessionFactory sessionFactory;

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
