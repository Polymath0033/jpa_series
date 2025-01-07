package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Comment;
import org.example.entities.Post;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String persistenceUnitName = "jpa_2023_c1_e6";
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");
        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(persistenceUnitName),props);
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

//            Post p = new Post();
//            p.setTitle("Post 1");
//            p.setContent("Post 1 content");
//            Comment c1 = new Comment();
//            c1.setComment("Comment 1");
//            c1.setPost(p);
//            p.setComments(List.of(c1));
//            em.persist(p);
            //em.persist(c1);
            Post post = new Post();
            post.setTitle("Post 1");
            post.setContent("This is a post 1 description");

            Comment c1 = new Comment();
            c1.setComment("This is a comment 1");
            Comment c2 = new Comment();
            c2.setComment("This is a comment 2");
            post.setComments(List.of(c1, c2));
            em.persist(post);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        System.out.println("Hello, World!");
    }
}