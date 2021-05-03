import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Observable;

public class table extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane p = new Pane();
        TableView<anime> tv = new TableView<anime>();
        TableColumn<anime,String> tb = new TableColumn<anime, String>("Name");
        tb.setMaxWidth(300);
        tb.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<anime,String> tb1 = new TableColumn<anime, String>("Genra");
        tb1.setMaxWidth(300);
        tb1.setCellValueFactory(new PropertyValueFactory<>("genra"));
        TableColumn<anime,String> tb2 = new TableColumn<anime, String>("Score");
        tb2.setMaxWidth(300);
        tb2.setCellValueFactory(new PropertyValueFactory<>("score"));
        tv.setItems(getAnime());
        tv.getColumns().addAll(tb,tb1,tb2);
        p.getChildren().add(tv);
        Scene s = new Scene(p,600,500);
        primaryStage.setScene(s);
        primaryStage.show();
    }
    public ObservableList<anime> getAnime(){
        ObservableList<anime> anime = FXCollections.observableArrayList();
      //  anime.add(new anime("cow","kgf","854"));
        return anime;
    }
}
