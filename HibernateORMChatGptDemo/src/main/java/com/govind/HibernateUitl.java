package com.govind;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUitl {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // create the sessionfactory from the hibernate.cfg.xml
            return new Configuration().configure(new File("src/main/java/hibernate.cfg.xml")).addAnnotatedClass(Employee.class).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial session factory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}
