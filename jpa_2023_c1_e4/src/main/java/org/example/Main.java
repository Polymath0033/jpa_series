package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Employee;
import org.example.entities.Product;
import org.example.entities.Student;
import org.example.entities.keys.StudentKey;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String persistenceUnitName = "jpa_2023_c1_e4";
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
       props.put("hibernate.hbm2ddl.auto", "create");
        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(persistenceUnitName),props);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
//            Employee employee = new Employee();
//            employee.setName("George");
//            employee.setAddress("Some Address");
//            em.persist(employee);
//            Employee george = em.find(Employee.class, employee.getId());
//            System.out.println(george);
//            Product product = new Product();
//            product.setCode("ABC");
//            product.setNumber(1234);
//            product.setColor("Blue");
//            em.persist(product);
            StudentKey sk = new StudentKey();
            sk.setCode("ABC");
            sk.setNumber(10);
            Student student = new Student();
            student.setId(sk);
            student.setName("John Doe");
            em.persist(student);
            em.getTransaction().commit();
        }finally {
            em.close();
        }

        System.out.println("Hello, World!");
    }
}