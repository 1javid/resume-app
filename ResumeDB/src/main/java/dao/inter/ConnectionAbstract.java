package dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionAbstract {

     protected static Connection connect() throws Exception {

         String url = "jdbc:mysql://localhost:3306/resume";
         String username = "root";
         String password = "Vusacanu123!";
         Connection c = DriverManager.getConnection(url, username, password);

         return c;
    }
}
