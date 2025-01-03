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
        String pUName = "jpa_2023_c1_e2";
        Map<?, ?> props = new HashMap<>();
        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(pUName),props);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Employee e1 = em.find(Employee.class, 1);
            Employee emp = new Employee();
           emp.setId(1);
           emp.setName("Mary");
           emp.setAddress("address");
           em.merge(emp);
//            if(e1 != null){
//                e1.setName("Jack Smith");
//                e1.setName("John Doe");
//                e1.setName("Jack Smith");
//            }
//            Entity operations
//            em.persist();=>Adding entity, it does more than this though
//            em.find(); => Find by PK, Get from DB and add it to the context if it doesn't already exist
//            em.remove(); =>Marking entity for removal
//            em.merge();=>Merges an entity from outside context to context.
//            em.refresh();=>Mirror the context from the DB
//            em.detach();=>Taking the entity out of the context


            em.getTransaction().commit();
        }finally{
            em.close();
        }
        System.out.println("Hello, World!");
    }
}