import java.sql.SQLException;

public class currentUser {
    private String user = "";
    private int user_id ;
    private dbconnection db;
    currentUser(dbconnection db){
        this.db = db;
    }
    public void logged(String username) throws SQLException {
        user = username;
        user_id = db.getUserId(user);
    }
    public String getCurrentUsername(){
        return user;
    }
    public int getUser_id(){
        return user_id;
    }

}
