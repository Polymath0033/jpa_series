package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.dto.CountedEnrollmentForStudent;
import org.example.dto.EnrolledStudent;
import org.example.entities.Student;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        String persistenceUnitName = "jpa_2023_c1_e10";
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "none");
        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(persistenceUnitName),props);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
//            String jpql = """
//                    SELECT s,e FROM Student s INNER JOIN s.enrollments e
//                    """;

//            String jpql = """
//                    SELECT s,e FROM Student s JOIN s.enrollments e
//                    """;
//            String jpql = """
//                    SELECT s,e FROM Student s, Enrollment e WHERE  s.id= e.student.id
//                    """;

//            String jpql = """
//                    SELECT s,e FROM Student s, Enrollment e WHERE  s= e.student
//                    """;

//            String jpql = """
//                    SELECT s,e FROM Student s LEFT JOIN s.enrollments e
//                    """;

//            String jpql = """
//                    SELECT NEW org.example.dto.EnrolledStudent(s,e) FROM Student s RIGHT JOIN s.enrollments e
//                    """;
            //            TypedQuery<EnrolledStudent> q = em.createQuery(jpql, EnrolledStudent.class);
//            q.getResultList().forEach(o -> System.out.println(o.enrollment()+""+o.student()));


//            String jpql = """
//                    SELECT s FROM Student s WHERE
//                    (SELECT COUNT(e) FROM Enrollment e WHERE e.student.id=s.id)>1
//                    """;
//
//            TypedQuery<Student> q = em.createQuery(jpql, Student.class);
//            q.getResultList().forEach(System.out::println);

            String jpql = """
                    SELECT NEW org.example.dto.CountedEnrollmentForStudent(s, (SELECT count(e) FROM Enrollment e WHERE e.student=s)) FROM Student s
                    """;
            TypedQuery<CountedEnrollmentForStudent> q = em.createQuery(jpql, CountedEnrollmentForStudent.class);
            q.getResultList().forEach(System.out::println);

            em.getTransaction().commit();
        }finally {
            em.close();
        }
        System.out.println("Hello, World!");
    }
}