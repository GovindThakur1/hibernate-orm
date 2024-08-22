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

        // Below code is to insert into the database using hiberntae
        /*
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
         */


        // Fetching the data from the database using the hibernate

        /*
        Alien alien2 = new Alien();

        Configuration con = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml")).addAnnotatedClass(Alien.class);

        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();

        SessionFactory sessionFactory = con.buildSessionFactory(sr);
        Session ss = sessionFactory.openSession();
        Transaction transaction = ss.beginTransaction();
        alien2 = (Alien) ss.get(Alien.class, 102);  // the class name and the primary key
        transaction.commit();
        System.out.println(alien2);

         */


        // Below demo is for embeddable object.

        AlienName an = new AlienName();
        an.setFname("govind");
        an.setLname("thakur");

        Alien alien3 = new Alien();
        alien3.setAid(110);
        alien3.setAname(an);
        alien3.setColor("yellow");


        Configuration conf = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml")).addAnnotatedClass(Alien.class);

        ServiceRegistry srv = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();

        SessionFactory sff = conf.buildSessionFactory(srv);

        Session ssn = sff.openSession();
        Transaction transaction = ssn.beginTransaction();
        ssn.persist(alien3);
        transaction.commit();


    }
}
