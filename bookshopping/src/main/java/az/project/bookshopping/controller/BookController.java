package az.project.bookshopping.controller;



import az.project.bookshopping.configuration.MySession;
import az.project.bookshopping.entity.Book;
import az.project.bookshopping.file.StorageService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Controller
public class BookController {

    private final StorageService storageService;

    private final az.project.bookshopping.repository.BookRepository BookDAO;
    private final MySession mySession;

    public BookController(az.project.bookshopping.repository.BookRepository BookDAO, StorageService storageService, MySession mySession) {
        this.BookDAO = BookDAO;
        this.storageService = storageService;
        this.mySession = mySession;
    }

    // Books Page
    @GetMapping(path = "/books")
    public String ShowBooks(Model model){
        List<Book> books = BookDAO.findAllByUsername(mySession.getUsername());
        model.addAttribute("books",books);
        model.addAttribute("username", "Username: "+mySession.getUsername());
        return "books";
    }

    // Book registration page
    @GetMapping(path = "/books/new")
    public String openNewBookPage(Model model){
        Book book = new Book();
        model.addAttribute("book",book);
        model.addAttribute("header","New Book");

        return "new-book";
    }

    // Book Registration
    @PostMapping(path = "/books/new-book-process")
    public String saveBook(@Valid @ModelAttribute(name="book") Book book, BindingResult result,
                           @RequestParam(value = "imageFile",required = false) MultipartFile imageFile, Model model){
        if(result.hasErrors()){
            return "new-book";
        }

        book.setUsername(mySession.getUsername());
        if( imageFile.isEmpty () && book.getId ()!=null){
            book.setImage(BookDAO.findById (book.getId ()).get().getImage ());
        }
        else{
            book.setImage(storageService.store(imageFile));
        }

        BookDAO.save(book);
        List<Book>books = BookDAO.findAll();
        model.addAttribute("books",books);
        return "redirect:/books";
    }

    // Deleting book
    @GetMapping(path = "/books/delete/{id}")
    public String deleteBook(@PathVariable(name = "id") Integer id, Model model){
        boolean bookExists = BookDAO.existsById(id);
        if(bookExists){
            BookDAO.deleteById(id);
        }
        List<Book> books = BookDAO.findAll();
        model.addAttribute("books",books);
        return "redirect:/books";
    }
    @GetMapping(path = "/books/edit/{id}")
    public String editBook(@PathVariable(name = "id") Integer id, Model model){
        Optional<Book> bookOptional = BookDAO.findById(id);
        boolean bookExists = bookOptional.isPresent()   ;
        Book book = new Book();
        if(bookExists){
            book = bookOptional.get();
        }
        model.addAttribute("book",book);
        model.addAttribute("header","Edit Book");

        return "new-book";
    }

}