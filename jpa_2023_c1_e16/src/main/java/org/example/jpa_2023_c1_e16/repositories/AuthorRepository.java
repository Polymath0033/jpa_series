package org.example.jpa_2023_c1_e16.repositories;

import org.example.jpa_2023_c1_e16.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
//
//public interface AuthorRepository extends Repository<Author,Integer> {
//    @Query("""
//SELECT a FROM Author a WHERE a.id = :id
//""")
//    Optional<Author> findAuthorById(Integer id);
//    @Query("""
//SELECT a FROM Author a
//""")
//    List<Author> findAll();
//}

public interface AuthorRepository extends JpaRepository<Author,Integer> {

    @Query("""
SELECT a FROM Author a WHERE a.id = :id
""")
    Optional<Author> findAuthorById(int id);

    @Query("""
SELECT a FROM Author a
""")
    List<Author> findAllAuthors();

}
