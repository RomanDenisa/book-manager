package repository;

import domain.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BookDbRepository  implements BookRepository{

    private JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    public BookDbRepository(Properties props){
        dbUtils=new JdbcUtils(props);
    }


    @Override
    public Book findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Book> getAll() {
        logger.traceEntry("Getting all books");
        List<Book> bookList = new ArrayList<>();
        try(Connection con = dbUtils.getConnection();
            PreparedStatement prepSt = con.prepareStatement("select * from BooksRead")) {
            try(ResultSet result = prepSt.executeQuery()) {
                while(result.next()) {
                    int bookId = result.getInt("book_id");
                    String title = result.getString("title");
                    String author = result.getString("author");
                    String finishDate = result.getString("finishDate");
                    int rating = result.getInt("rating");
                    Book book = new Book(title,author,finishDate,rating);
                    book.setId(bookId);
                    bookList.add(book);
                }
            }
        }
        catch(SQLException ex) {
            logger.error(ex.getMessage());
            System.out.println("SQL Error" + ex.getMessage());
        }
        logger.traceExit("Returned book list with {} elements", bookList.size());
        return bookList;
    }

    @Override
    public Book add(Book entity) {
        logger.traceEntry("Adding book {}",entity);
        try(Connection con = dbUtils.getConnection();
            PreparedStatement prepSt = con.prepareStatement("insert into BooksRead(title,author,finishDate,rating) " +
                    "values (?,?,?,?)")) {
            prepSt.setString(1,entity.getTitle());
            prepSt.setString(2,entity.getAuthor());
            prepSt.setString(3,entity.getFinishDate());
            prepSt.setInt(4,entity.getRating());
            int result = prepSt.executeUpdate();
            if(result > 0){
                logger.traceExit("Added successfully {}",entity);
                return null;
            }
        }
        catch(SQLException ex) {
            logger.error(ex.getMessage());
            System.out.println("SQL Error" + ex.getMessage());
        }
        logger.traceExit("Couldn't add book :( {}",entity);
        return entity;
    }

    @Override
    public Book delete(Integer integer) {
        return null;
    }

    @Override
    public Book update(Book entity) {
        return null;
    }
}
