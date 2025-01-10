package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.example.dto.CountedEnrollmentForStudent;
import org.example.entities.Student;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String persistenceUnitName = "jpa_2023_c1_e11";
        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");

        EntityManagerFactory emf =new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(persistenceUnitName), props);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
//            String jpql = """
//                    SELECT NEW org.example.dto.CountedEnrollmentForStudent(s.name, COUNT(s))
//                    FROM Student s
//                    GROUP BY s.name
//                    HAVING s.name LIKE '%e'
//                    ORDER BY s.name DESC
//                    """;
//            TypedQuery<CountedEnrollmentForStudent> q = em.createQuery(jpql, CountedEnrollmentForStudent.class);
//            q.getResultList().forEach(o-> System.out.println(o.s()+" " +o.count()));

            TypedQuery<Student> q = em.createNamedQuery("getAllEnrolledStudents", Student.class);
            q.getResultList().forEach(System.out::println);
            em.getTransaction().commit();
        }finally {
            em.close();
        }

        System.out.println("Hello, World!");
    }
}