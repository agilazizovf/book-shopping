package az.project.bookshopping.rest;


import az.project.bookshopping.entity.Book;
import az.project.bookshopping.entity.SearchModel;
import az.project.bookshopping.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/rest/books")
public class BookRestController {
    @Autowired
    private BookRepository BookDAO;

    @GetMapping

    public List<Book> findAll(){
        return BookDAO.findAll();
    }

    @GetMapping(path="/{id}")

    public Book findByID(@PathVariable(name = "id") Integer id){
        return BookDAO.findById(id).get();
    }

    @PostMapping(path="/search")
    public List<Book> findAllSearch(@RequestBody SearchModel search){
        // return BookDAO.findAllSearch(search.getSearch());
        return BookDAO.findAllSearchAllFields(search.getSearch());
    }

    @PostMapping(path="/search-find-partial")
    public List<Book> findAllSearchFindPartial(@RequestBody SearchModel search){
        // return BookDAO.findAllSearch(search.getSearch());
        return BookDAO.findAllSearchAllFieldsFindPartial(search.getSearch(), search.getBegin(), search.getLength());
    }
}