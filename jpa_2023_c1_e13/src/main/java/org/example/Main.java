package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.Customer;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String persistenceUnitName = "jpa_2023_c1_e13";
        Map<String,String> props = new HashMap<String, String>();
        props.put("hibernate.show_sql", "true");

        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(persistenceUnitName),props);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);

            Root<Customer> customerRoot = cq.from(Customer.class);

            cq.select(customerRoot);
            TypedQuery<Customer> query = em.createQuery(cq);

            query.getResultList().forEach(System.out::println);

            em.getTransaction().commit();
        }finally {
            em.close();
        }

        System.out.println("Hello, World!");
    }
}