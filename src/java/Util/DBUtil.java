package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lonely
 */
public class DBUtil {
    
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lsi", "root", "");
   
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error");
            }
            return connection;
        }
    }
}
