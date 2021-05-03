

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane mainPane =  new mainPane();

        Scene scene = new Scene(mainPane,500,500);


        primaryStage.setTitle("s");
        primaryStage.setScene(scene);
        primaryStage.show();

    //    regStage
       // regStage.show();
    }


   /* public static void main(String[] args) {
        launch(args);
    }*/
}