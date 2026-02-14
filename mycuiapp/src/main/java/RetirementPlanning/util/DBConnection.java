package RetirementPlanning.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/retirement_planning";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "jayaprakash@2005";

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                DB_URL,
                USERNAME,
                PASSWORD
        );
    }
}
