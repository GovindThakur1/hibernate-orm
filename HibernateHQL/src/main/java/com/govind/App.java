package com.govind;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.transform.Transformers;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration config = new Configuration().configure(new File("src/main/java/hibernate.cfg.xml")).addAnnotatedClass(Student.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

        SessionFactory sf = config.buildSessionFactory(serviceRegistry);
        Session session = sf.openSession();
        session.beginTransaction();
        Random r = new Random();

        // Insert random data into the table.
        /*
        for (int i = 1; i <= 50; i++) {
            Student student = new Student();
            student.setRollno(i);
            student.setName("Name " + i);
            student.setMarks(r.nextInt(100));
            session.persist(student);
        }
        */

        // Now retrieving the data using HQL

        // Selecting all the data i.e., select * from Student
        Query<Student> query = session.createQuery("from Student", Student.class);
        List<Student> students = query.list();
        for (Student student : students) {
            System.out.println(student);
        }

        // HQL with where clause
        System.out.println("\nStudents with roll no more than 30\n");
        Query<Student> query1 = session.createQuery("from Student where rollno > 30", Student.class);
        List<Student> students1 = query1.list();
        for (Student student : students1) {
            System.out.println(student);
        }


        // HQL to get a specific result.
        System.out.println();
        Query<Student> query2 = session.createQuery("from Student where rollno = 7", Student.class);
        Student student = (Student) query2.uniqueResult();
        System.out.println(student);

        // selecting multiple columns for a same row
        // This will return an array of type Object containing each attribute's values.
        System.out.println();
        String hql = "select rollno, name, marks from Student where rollno = 6";
        Object[] objects = (Object[]) session.createQuery(hql).uniqueResult();
        System.out.println("The values returned are: ");
        for (Object o : objects) {
            System.out.print(o + " ");
        }


        // selecting multiple or particular columns for all the rows
        // it will return an list containing array of type object which contains each attribute's values for each rows.
        System.out.println();
        hql = "select rollno, name, marks from Student";
        List<Object[]> objects1 = session.createQuery(hql).list();

        for (Object[] studentRowArray : objects1) {
            for (Object attribute : studentRowArray) {
                System.out.print(attribute + " ");
            }
            System.out.println();
        }


        // We can also use aliases in above query
        System.out.println();
        hql = "select rollno, name, marks from Student s where s.rollno > 40";
        List<Object[]> objects2 = session.createQuery(hql).list();

        for (Object[] studentRowArray : objects2) {
            for (Object attribute : studentRowArray) {
                System.out.print(attribute + " ");
            }
            System.out.println();
        }

        // using setParameter() function.
        int minMarks = 60;
        // getting the sum of marks
        Long marksSum = (Long) session
                .createQuery("select sum(marks) from Student s where s.marks > :minMark", Long.class)
                .setParameter("minMark", minMarks)
                .uniqueResult();
        System.out.println("The sum of marks is: " + marksSum);



        /*
         * **************************************************************************************************
         * Using SQL instead of HQL
         */
        String sql = "SELECT rollno, name, marks FROM Student WHERE rollno = 7";
        Object[] result = (Object[]) session.createNativeQuery(sql).uniqueResult();

        for (Object s : result) {
            System.out.println(s);
        }


        NativeQuery<Student> query3 = session.createNativeQuery("SELECT name, marks FROM Student WHERE marks > 60");
        query3.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Student> stds = query3.list();

        for (Object o : stds) {
            Map m = (Map) o;
            System.out.println(m.get("name") + " : " + m.get("marks"));
        }


        session.getTransaction().commit();
        session.close();


    }
}
