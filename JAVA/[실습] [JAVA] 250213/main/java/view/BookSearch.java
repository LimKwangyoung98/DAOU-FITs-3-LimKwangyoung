package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookSearch extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = null;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("booksearch.fxml"));

        try {
            root = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("도서 검색");

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
