
import java.sql.*;

public class App {
    public static void main(String[] args) {

        App app = new App();
        app.start();

    }

    public void start(){
        String url = "jdbc:mysql://localhost/";
        String dbName = "test";
        String userName = "root";   // default
        String password = "";
        try ( Connection conn =
                      DriverManager.getConnection(url + dbName, userName, password) )
        {
            System.out.println("SUCCESS ! - Your program has successfully connected to the MySql Database Server. Well done.");
            System.out.println("... we could query the database using the SQL commands you learned in DBMS...");
            System.out.println("... but for now, we will simply close the connection.");

            System.out.println("Your program is disconnecting from the database - goodbye.");
        }
        catch (SQLException ex)
        {
            System.out.println("Failed to connect to database - check that you have started the MySQL from XAMPP, and that your connection details are correct.");
            ex.printStackTrace();
        }
    }
}