package com.jcg.hibernate.crud.operations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

public class DbOperations {

    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    public final static Logger logger = Logger.getLogger(DbOperations.class);

    
    
    // This Method Is Used To Create The Hibernate's SessionFactory Object
    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");

        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }

    // Method 1: This Method Used To Create A New Department Record In The Database Table
    public static void createDepartment(String name, String region) {

        Department department = null;

        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            // Creating Transaction Entities
            department = new Department();
            department.setName(name);
            department.setRegion(region);

            sessionObj.save(department);

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            logger.info("\nSuccessfully Created  Records In The Database!\n");
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    public static void createEmployeewithDept(String Dept) {

        Department department = null;

        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            Criteria cr = sessionObj.createCriteria(Department.class);
            cr.add(Restrictions.eq("name", Dept));
            List list = cr.list();
            Integer departmentId = null;
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                Department d1 = (Department) iterator.next();
                departmentId = d1.getDepartmentId();

            }

            // Creating Transaction Entities
            department = (Department) sessionObj.get(Department.class, departmentId);

            List<Employee> employees = new ArrayList<>();

            employees.add(new Employee("emp1", "password", 40, 1200000, "Active", "Mumbai", "12344", "MH", "IN"));
            employees.add(new Employee("emp2", "password", 42, 200000, "Active", "Mumbai", "12344", "MH", "IN"));
            employees.add(new Employee("emp3", "password", 32, 1200000, "Active", "Mumbai", "12344", "MH", "IN"));
            employees.add(new Employee("emp4", "password", 40, 1200000, "Active", "Mumbai", "12344", "MH", "IN"));
            employees.add(new Employee("emp5", "password", 34, 1200000, "Active", "Mumbai", "12344", "MH", "IN"));
            employees.add(new Employee("emp6", "password", 45, 300000, "Active", "Mumbai", "12344", "MH", "IN"));
            employees.add(new Employee("emp7", "password", 50, 1400000, "Active", "Mumbai", "12344", "MH", "IN"));
            employees.add(new Employee("emp8", "password", 55, 400000, "Active", "Mumbai", "12344", "MH", "IN"));
            employees.add(new Employee("emp9", "password", 40, 500000, "Active", "Mumbai", "12344", "MH", "IN"));

            department.setEmployeeCollection(new ArrayList<Employee>());
            for (Employee emp : employees) {

                department.getEmployeeCollection().add(emp);
                emp.setDepartmentId(department);
                sessionObj.save(emp);

            }

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            logger.info("\nSuccessfully Created  Records In The Database!\n");
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }

    public static void deleteAllRecords() {
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();

            Query queryObj = sessionObj.createQuery("DELETE FROM Employee");
            queryObj.executeUpdate();

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            logger.info("\nSuccessfully Deleted All Records From The Database Table!\n");
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }
}
