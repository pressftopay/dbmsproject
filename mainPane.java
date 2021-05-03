import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;


public class mainPane extends Pane {
    private dbconnection db;
    private currentUser us;
    private String user;
    private Label s = new Label("You must log in,firstly");
    public final Button menu1 = new Button("Find Anime");
    public final Button menu2 = new Button("Add a review");
    public final Button menu3 = new Button("Login/Register");
    public final Button menu4 = new Button("Help");
    public HBox menu ;

        mainPane(){
            try {
                db = new dbconnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            us = new currentUser(db);
            user = us.getCurrentUsername();

            menuBar();


        findAnime();
        menu.toFront();
        getChildren().add(menu);
    }
    public void menuBar(){

        menu  = new HBox();
        setPod(menu,50,20);
        menu.getChildren().addAll(menu1, menu2, menu3,menu4);
        menu1.setOnAction(event -> {
            remove();
            findAnime();

        });
        menu2.setOnAction(event -> {
            remove();
            addRev();

        });
        menu3.setOnAction(event -> {
            loginReg();
        });

    }
    public void findAnime(){
            Pane fA = new Pane();
            fA.setLayoutX(10);
            fA.setLayoutY(50);
        Label namaA = new Label("Anime Name");
        Label genresA = new Label("Genres Name");
        Label typeA = new Label("Type Name");
        setPod(namaA,5,55); setPod(genresA,5,105);setPod(typeA,5,155);
        TextField name = new TextField();
        TextField genres = new TextField();
        TextField type = new TextField();
        setPod(name,100,50); setPod(genres,100,100);setPod(type,100,150);

        Button serch = new Button("Search");
        setPod(serch,300,200);

        serch.setOnMouseClicked(event -> {
            System.out.println("Searching");
            Stage news = new Stage();
            Pane p = new Pane();
            try {
                p.getChildren().add(db.showAnime(name.getText(),genres.getText(),type.getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Scene s  = new Scene(p,2000,1000);
            news.setScene(s);
            news.show();
        });
        fA.getChildren().addAll(name,genres,type,namaA,genresA,typeA,serch);
            getChildren().add(fA);

    }
    public void addRev(){

        TextField name = new TextField();
        TextField type = new TextField();
        Label namaA = new Label("Anime Name");

        Label score = new Label("Score ");
            Pane rev = new Pane();
            rev.setLayoutX(10);
            rev.setLayoutY(50);
            Button sa = new Button("Enter");
        setPod(name,100,50); setPod(type,100,150);setPod(namaA,5,55);setPod(score,5,155);
          sa.setOnMouseClicked(event -> {
              if(us.getCurrentUsername().equals("")){
                  setPod(s,300,300);
                  if(!rev.getChildren().contains(s)){
                      s.setTextFill(Color.RED);
                      rev.getChildren().addAll(s);
                      FadeTransition ft = new FadeTransition(Duration.seconds(2.5),s);
                      ft.setFromValue(1);
                      ft.setToValue(0);
                      ft.play();
                      ft.setOnFinished(event1 -> {
                          rev.getChildren().remove(s);
                      });
                  }

              }else if (Integer.valueOf(type.getText()) > 0 && Integer.valueOf(type.getText()) < 10){
                  try {
                      db.revIns(us.getUser_id(),namaA.getText(),Integer.valueOf(type.getText()));
                      namaA.setText("");
                      type.setText("");
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
              }
          });
            rev.getChildren().addAll(sa,name,type,namaA,score);
            setPod(sa,300,200);
            getChildren().add(rev);
    }
    public void loginReg(){
        Pane reg = new Pane();
        Label logreg = new Label("Login/Register Form");
        Label userN = new Label("Username");
        Label pass = new Label("Password");
        TextField username = new TextField();
        PasswordField password = new PasswordField();

        Button login = new Button("Login");
        Button regis = new Button("Register");
        regis.setOnAction(event -> {
            try {
                if(username.getText().equals("") ){
                    username.setPromptText("Write username");
                }else if( password.getText().equals("")){
                    password.setPromptText("Write password");
                }else {
                db.regUser(username.getText(),password.getText());
                    username.setText("");
                    password.setText("");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        setPod(login,50,250);setPod(regis,200,250);setPod(password,50,170);setPod(username,50,100);
        setPod(userN,50,80);setPod(pass,50,150);setPod(logreg,50,20);
        reg.getChildren().addAll(login,regis,username,password,userN,pass,logreg);
        Scene regS = new Scene(reg,300,300);
        Stage regStage = new Stage();
        regStage.initModality(Modality.APPLICATION_MODAL);
        regStage.setScene(regS);
        regStage.show();
        login.setOnAction(event -> {
            try {
                if(username.getText().equals("") ){
                    username.setPromptText("Write username");
                }else if( password.getText().equals("")){
                    password.setPromptText("Write password");
                }else {
                if( db.logUser(username.getText(),password.getText())){
                    us.logged(username.getText());
                    System.out.println(us.getUser_id());
                    regStage.close();

                }else{
                    username.setPromptText("Invalid username");
                    password.setPromptText("Invalid password");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }
    public void setPod(Node n , double x,double y){
            n.setLayoutX(x);
            n.setLayoutY(y);
    }
    public void remove(){
        if(!getChildren().isEmpty()) {
            getChildren().clear();
            getChildren().add(menu);
        }
    }
}
