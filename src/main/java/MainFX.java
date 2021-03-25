import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.BookService;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Welcome to your book manager!");
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/booksView.fxml"));
        AnchorPane root=loader.load();
        MainController ctrl=loader.getController();
        Scene myScene = new Scene(root);
        primaryStage.setScene(myScene);
        setServices(ctrl);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    static void setServices(MainController controller){
        ApplicationContext context=new ClassPathXmlApplicationContext("booksConfig.xml");
        BookService bookService = context.getBean(BookService.class);
        controller.setService(bookService);
    }
}
