package controller;

import domain.Book;
import domain.validator.ValidatorException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.BookService;
import service.ServiceException;
import utils.Observer;


public class MainController implements Observer {

    private BookService bookService;
    ObservableList<Book> model = FXCollections.observableArrayList();

    @FXML
    TableView<Book> bookTable;

    @FXML
    TableColumn<Book,String> title, author,finDate,rating;

    @FXML
    TextField titleBox,authorBox;

    @FXML
    DatePicker finishDatePicker;

    @FXML
    ChoiceBox<String> ratingBox;

    public void setService(BookService bookService){
        this.bookService=bookService;
        bookService.addObserver(this);
        initModel();
    }

    public void initialize(){
        title.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        finDate.setCellValueFactory(new PropertyValueFactory<Book, String>("finishDate"));
        rating.setCellValueFactory(new PropertyValueFactory<Book, String>("rating"));
        bookTable.setItems(model);
        ratingBox.setItems(FXCollections.observableArrayList("1","2","3","4","5"));
    }

    public void initModel(){
        model.setAll(bookService.getAll());
    }


    /**
     * add book
     * @param actionEvent click of button
     */
    public void handleAddBook(ActionEvent actionEvent) {
        String ratingChoice = ratingBox.getValue();
        if(ratingChoice == null || titleBox.getText()==null || authorBox.getText()==null || finishDatePicker.getValue()==null)
            MessageAlert.showMessage(null, Alert.AlertType.ERROR, "Invalid field", "No field value :(");
        else{
            try {
                bookService.addBookRead(titleBox.getText(),authorBox.getText(),finishDatePicker.getValue().toString(),
                        Integer.parseInt(ratingBox.getValue()));
                MessageAlert.showMessage(null, Alert.AlertType.CONFIRMATION,"Success!","Yay! Book added successfully! :)");
                clearFields();
            } catch (ValidatorException | ServiceException ex) {
                MessageAlert.showMessage(null, Alert.AlertType.ERROR, "Unsuccessful action!", ex.getMessage());
            }
        }
    }

    /**
     * clears fields in the interface
     */
    private void clearFields() {
        authorBox.setText("");
        titleBox.setText("");
        ratingBox.setValue("1");
    }

    @Override
    public void update() {
        initModel();
    }

    /**
     * exports books to pdf
     * @param actionEvent click of the button
     */
    public void handleExport(ActionEvent actionEvent) {
        bookService.exportToPdf();
        MessageAlert.showMessage(null, Alert.AlertType.CONFIRMATION,"Success!","Exported to PDF successfully!");
    }
}
