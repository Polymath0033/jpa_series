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
        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(persistenceUnitName),props);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Employee emp = new Employee();
            Employee emp1 = em.find(Employee.class, 1);
            if (emp1 != null) {
                System.out.println(emp1.toString());
            }



            em.getTransaction().commit();
        }finally {
            em.close();
        }
        System.out.println("Hello, World!");
    }
}