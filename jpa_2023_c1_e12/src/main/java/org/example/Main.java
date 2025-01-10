package org.example;

import jakarta.persistence.*;
import org.example.entities.DistinctStudent;
import org.example.entities.Student;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String persistenceUnitName = "jpa_2023_c1_e12";
        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");

        EntityManagerFactory emf =new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(persistenceUnitName), props);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
//            String sql = """
//                    SELECT * FROM student
//                    """;
//            Query query = em.createNativeQuery(sql, Student.class);
//            query.getResultList().forEach(o -> System.out.println(o));


//            String sql = """
//                    SELECT s FROM DistinctStudent s
//                    """;
//            TypedQuery<DistinctStudent> q =em.createQuery(sql, DistinctStudent.class);
//            q.getResultList().forEach(System.out::println);


              StoredProcedureQuery storedProcedureQuery =em.createStoredProcedureQuery("GetStudents", Student.class)
                    .registerStoredProcedureParameter("id",Integer.class, ParameterMode.IN)
                    .setParameter("id",2);
              storedProcedureQuery.getResultList().forEach(System.out::println);
            em.getTransaction().commit();

        }finally {
            em.close();
        }

        System.out.println("Hello, World!");
    }
}