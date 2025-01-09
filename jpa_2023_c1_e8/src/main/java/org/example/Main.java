package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Book;
import org.example.entities.ElectronicDevice;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String persistenceUnitName = "jpa_2023_c1_e8";
        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");
        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(persistenceUnitName),props);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Book book = new Book();
            book.setId(1L);
            book.setAuthor("John Doe");
            ElectronicDevice device = new ElectronicDevice();
            device.setId(2L);
            device.setVoltage(20);

            em.persist(book);
            em.persist(device);
            var sql = "SELECT p FROM Product p";
            em.createQuery(sql, Product.class).getResultList().forEach(System.out::println);


            em.getTransaction().commit();
        }finally{
            em.close();
        }
        System.out.println("Hello, World!");
    }
}