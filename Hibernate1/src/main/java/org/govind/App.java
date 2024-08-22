package org.govind;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;

/**
 * Hibernate demo first.
 */
public class App {
    public static void main(String[] args) {
        Alien alien = new Alien();
        alien.setAid(102);
        alien.setAname("gopal");
        alien.setColor("green");


        Configuration config = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml")).addAnnotatedClass(Alien.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
                .build();

        SessionFactory sf = config.buildSessionFactory(serviceRegistry);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(alien);
        tx.commit();


    }
}
