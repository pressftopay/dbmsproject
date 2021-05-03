import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jdk.nashorn.internal.codegen.types.Type;


import java.sql.*;

public class dbconnection {

    private String url;
    private Connection conn;
    private currentUser cu ;


    dbconnection() throws SQLException {
        url = "jdbc:oracle:thin:@localhost:1521:xe";
        conn = DriverManager.getConnection(url,"hr","hr");
        if(conn !=null){
            System.out.println("good");
        }

    }
    public void regUser(String username,String password) throws SQLException {
        CallableStatement s = conn.prepareCall("call usersp.regUser(?,?)");
        s.setString(1,username);
        s.setString(2,password);
        s.execute();
        s.close();
    }
    public int getUserId(String usermane) throws SQLException {
        int used_id = 0;
        String sql = "select user_id from users where " +
                "username = " + "'"+usermane+"'";
        Statement s = conn.createStatement();
        ResultSet res = s.executeQuery(sql);
        while(res.next()){
        used_id = res.getInt(1);}
        return used_id;
    }
    public boolean logUser(String username,String password) throws SQLException {

        CallableStatement s = conn.prepareCall(" {? = call usersp.logUser(?,?)}");

        s.registerOutParameter(1,Types.INTEGER);
        s.setString(2,username);
        s.setString(3,password);

        s.execute();
       int res = s.getInt(1);
        if (res == 1){
          return true;
        }else {
            return false;
        }
    }
    public void revIns(int usrd_id,String name, int score) throws SQLException {
        CallableStatement s = conn.prepareCall("call ratingp.insert_rating(?,?,?)");
        //String sql = "update rating set rating = "+score+" where user_id = "+usrd_id+" and anime_id = 1";
       // Statement sa = conn.createStatement();
       /// sa.executeQuery(sql);
        s.setInt(1,usrd_id);
        s.setString(2,name);
        s.setInt(3,score);
        s.execute();
        s.close();
     //   int sa = s.executeUpdate();
    //    System.out.println(sa);

    }
    public TableView<anime> showAnime(String name, String genrd, String type) throws SQLException {
        String sql = "select * from anime where name like " + "'%"+name+"%' and genders like "+ "'%"+genrd+"%' and type like "+ "'%"+type+"%'";
        Statement s = conn.createStatement();
       ResultSet res=  s.executeQuery(sql);
        ObservableList<anime> anime = FXCollections.observableArrayList();

        while(res.next()){
           String names = res.getString(2);
           String score = res.getString(3);
           String genra  = res.getString(4);
           String ename = res.getString(5);
           String types = res.getString(6);
           String epis = res.getString(7);
           String aired = res.getString(8);
           String stud = res.getString(12);
           String source = res.getString(13);
           String duration = res.getString(14);
           String rating = res.getString(15);
            anime.add(new anime(names,score,genra,ename,types,epis,aired,stud,source,duration, rating));
        }
        TableView<anime> tv = new TableView<anime>();
        TableColumn<anime,String> tb = new TableColumn<anime, String>("Name");
        tb.setMaxWidth(200);
        tb.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<anime,String> tb1 = new TableColumn<anime, String>("Genra");
        tb1.setMaxWidth(300);
        tb1.setCellValueFactory(new PropertyValueFactory<>("genra"));
        TableColumn<anime,String> tb2 = new TableColumn<anime, String>("Score");
        tb2.setMaxWidth(100);
        tb2.setCellValueFactory(new PropertyValueFactory<>("score"));
        TableColumn<anime,String> tb3 = new TableColumn<anime, String>("English name");
        tb3.setMaxWidth(200);
        tb3.setCellValueFactory(new PropertyValueFactory<>("ename"));
        TableColumn<anime,String> tb4 = new TableColumn<anime, String>("Type");
        tb4.setMaxWidth(100);
        tb4.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<anime,String> tb5 = new TableColumn<anime, String>("Episodes");
        tb5.setMaxWidth(200);
        tb5.setCellValueFactory(new PropertyValueFactory<>("epis"));
        TableColumn<anime,String> tb6 = new TableColumn<anime, String>("Aired");
        tb6.setMaxWidth(320);
        tb6.setCellValueFactory(new PropertyValueFactory<>("aired"));
        TableColumn<anime,String> tb7 = new TableColumn<anime, String>("Studious");
        tb7.setMaxWidth(350);
        tb7.setCellValueFactory(new PropertyValueFactory<>("stud"));
        TableColumn<anime,String> tb8 = new TableColumn<anime, String>("Source");
        tb8.setMaxWidth(100);
        tb8.setCellValueFactory(new PropertyValueFactory<>("source"));
        TableColumn<anime,String> tb9 = new TableColumn<anime, String>("Duration");
        tb9.setMaxWidth(300);
        tb9.setCellValueFactory(new PropertyValueFactory<>("duration"));
        TableColumn<anime,String> tb10 = new TableColumn<anime, String>("Rating");
        tb10.setMaxWidth(400);
        tb10.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tv.setPrefWidth(2000);
        tv.setPrefHeight(1000);
        tv.setItems(anime);
        tv.getColumns().addAll(tb,tb1,tb2,tb3,tb4,tb5,tb6,tb7,tb8,tb9,tb10);
        return tv;
    }

}
