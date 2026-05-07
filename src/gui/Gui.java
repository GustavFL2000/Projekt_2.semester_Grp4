package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application {

    private Scene scene;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Gui Demo");

        scene = new Scene(new FrontPagePane(this), 400, 300);

        stage.setScene(scene);
        stage.show();
    }

    // 🔥 Denne bruger vi til at skifte side
    public void setPane(javafx.scene.layout.Pane pane) {
        scene.setRoot(pane);
    }
}