package homework.lesson4.javafx;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainHomeWorkLesson4 extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/sample.fxml"));
        stage.setTitle("Messanger");
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MainHomeWorkLesson4.class.getResource(fxml));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
       //launch(args);
        launch();
    }
}


