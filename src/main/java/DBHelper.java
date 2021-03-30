import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private Connection con;

    public DBHelper() throws SQLException {
        this.con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/bapers", "root", "");
    }

    public Connection getCon() {
        return con;
    }
}
