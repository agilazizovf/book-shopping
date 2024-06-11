package az.project.bookshopping.repository;

import az.project.bookshopping.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface BookRepository extends JpaRepository<Book, Integer> {

    ArrayList<Book> findAllByUsername(String username);

    @Query(value = "select * from book where name like %?1%",nativeQuery = true)
    ArrayList<Book> findAllSearch(String search);


    @Query(value = "select * from book where name like %?1% or description like  %?1% or price like  %?1% or page_count like  %?1% or author like  %?1%",nativeQuery = true)
    ArrayList<Book> findAllSearchAllFields(String search);

    @Query(value = "select * from book where name like %?1% or description like  %?1% or price like  %?1% or page_count like  %?1% or author like  %?1% limit ?2,?3",nativeQuery = true)
    ArrayList<Book> findAllSearchAllFieldsFindPartial(String search, Integer begin, Integer length);

}
