package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        String persistenceUnitName = "jpa_2023_c1_e9";
        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(persistenceUnitName),props);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            /*
            String jpql = "select p from Product p where p.price> :price AND p.name LIKE :name";
            TypedQuery<Product> q = em.createQuery(jpql, Product.class);
            q.setParameter("price", 5);
            q.setParameter("name", "%a%");
            List<Product> productList = q.getResultList();
            for (Product product : productList) {
                System.out.println(product);
            }
            */

           /*
            String jpql = "SELECT AVG(p.price) FROM Product p";
            TypedQuery<Double> q = em.createQuery(jpql, Double.class);
            Double avg = q.getSingleResult();
            System.out.println(avg);
            */

            /*
            String jpql = "select count(p) from Product p";
            TypedQuery<Long> q = em.createQuery(jpql, Long.class);
            Long count = q.getSingleResult();
            System.out.println(count);

             */

            /*
//            String jpql = "select p.name,p.price from Product p";
            String jpql = "SELECT p.name, AVG(p.price) FROM Product p GROUP BY p.name";
            TypedQuery<Object[]> q = em.createQuery(jpql, Object[].class);
            q.getResultList().forEach((objects -> System.out.println(objects[0]+" "+objects[1])));

             */

            String jpql = "SELECT p FROM Product p WHERE p.name LIKE 'Candy'";

            TypedQuery<Product> q = em.createQuery(jpql, Product.class);

            Optional<Product> p;
            try {
                p = Optional.of(q.getSingleResult());
            } catch (NoResultException e) {
                p = Optional.empty();
            }

            p.ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("Product not found")
            );
            em.getTransaction().commit();
        }finally {
            em.close();
        }

        System.out.println("Hello, World!");
    }
}