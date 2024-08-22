package com.govind;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeDAO {

    public void saveEmployee(Employee employee) {
        Session session = HibernateUitl.getSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        } finally {
            session.close();
        }
    }


    public Employee getEmployeeById(Long id) {
        Session session = HibernateUitl.getSessionFactory().openSession();
        Employee employee = null;

        try {
            employee = session.get(Employee.class, id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employee;
    }








}
