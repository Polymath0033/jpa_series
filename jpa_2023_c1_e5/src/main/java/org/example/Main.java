package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.entities.Passport;
import org.example.entities.Person;
import org.example.entities.User;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String persistenceUnitName = "jpa_2023_c1_e5";
        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql","true");
        props.put("hibernate.hbm2ddl.auto","create");
        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(persistenceUnitName),props);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Person person = new Person();
            person.setName("John Doe");
            Passport passport = new Passport();
            passport.setNumber("ABCD123");
            person.setPassport(passport);
            passport.setPerson(person);
            em.persist(person);
          //  em.persist(passport);
//            TypedQuery<Person> q = em.createQuery("select p from Person p where p.passport.number = :number", Person.class);
//            q.setParameter("number", "ABCD123");
//            if(!q.getResultList().isEmpty()) {
//                System.out.println(q.getResultList());
//            }


            User user = new User();
            user.setName("John Doe");
            user.setDescription("Default Description");
            em.persist(user);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        System.out.println("Hello, World!");
    }
}