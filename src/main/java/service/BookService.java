package service;

import domain.Book;
import domain.validator.Validator;
import repository.BookRepository;
import utils.Observable;
import utils.Observer;
import utils.PdfExport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class BookService implements Observable {

    /**
     * book repository
     */
    private BookRepository bookRepo;
    /**
     * book validator
     */
    private Validator<Book> bookVal;
    /**
     * observers list
     */
    List<Observer> observers = new ArrayList<>();

    public BookService(BookRepository bookRepo, Validator<Book> bookVal) {
        this.bookRepo = bookRepo;
        this.bookVal = bookVal;
    }

    /**
     * adds a new book
     * @param title - title of the book
     * @param author - author of the book
     * @param date - the time the reading was finished
     * @param rating - shows how well the book was perceived
     */
    public void addBookRead(String title, String author, String date, int rating){
        Book book = new Book(title,author,date,rating);
        bookVal.validate(book);
        Book book1 = bookRepo.add(book);
        if(book1 != null)
            throw new ServiceException("Couldn't add book!");
        notifyObservers();
    }

    /**
     * @return all books
     */
    public List<Book> getAll() {
        return StreamSupport.stream(bookRepo.getAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    /**
     * exports the list of books to pdf format
     */
    public void exportToPdf() {
        PdfExport.export(StreamSupport.stream(bookRepo.getAll().spliterator(),false)
                .collect(Collectors.toList()));
    }

    @Override
    public void addObserver(Observer e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers() {
        observers.stream().forEach(x->x.update());
    }
}
