package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Employee;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String persistenceUnitName = "jpa_2023_c1_e3";
        Map<String,String> props = new HashMap<>();
//        For showing how sql transaction works
        props.put("hibernate.show_sql", "true");
//        Used to create a table when it is never been initiated. This is not recommended on a production application
        props.put("hibernate.hbm2ddl.auto", "update");
//
         //  props.put("hibernate.hbm2ddl.auto", "update");
        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(persistenceUnitName),props);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Employee emp = new Employee();
            emp.setName("John Doe");
            emp.setAddress("Lagos");
            em.persist(emp);
//            var e1 = em.getReference(Employee.class, 1);
//            System.out.println(e1);
//            e1.setName("Hello World");
//            em.refresh(e1);

            em.getTransaction().commit();
        }finally {
            em.close();
        }
        System.out.println("Hello, World!");
    }
}