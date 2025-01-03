package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

       // EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_2023_c1_e1");
        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>());
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Product p = new Product();
            p.setId(2L);
            p.setName("Pepsi");
            em.persist(p);// add this to the context. NB: persist is not an insert query
            em.getTransaction().commit();
        }
        finally {
            em.close();
        }


        System.out.println("Hello, World!");
    }
}