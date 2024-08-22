package com.govind;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Alien a = null;

        Configuration configuration = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml")).addAnnotatedClass(Alien.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);


        // For below, the query will be shot only once since it is inside the same session, ie session1
//        Session session1 = sessionFactory.openSession();
//        Transaction transaction = session1.beginTransaction();
//        a = session1.get(Alien.class, 1);
//        System.out.println(a);
//        a = session1.get(Alien.class, 1);
//        System.out.println(a);
//        session1.getTransaction().commit();


        // For below, the query will run twice, since there are different sessions

        // session1
        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();
        a = session1.get(Alien.class, 1);
        System.out.println(a);
        session1.getTransaction().commit();
        session1.close(); // closing session1

        // session2
        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        a = session2.get(Alien.class, 2);
        System.out.println(a);
        session2.getTransaction().commit();
        session2.close();


    }
}
