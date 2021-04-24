import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class ConnectionDemo {
    private final Properties pr = new Properties();

    public void load(InputStream io) {
        try {
            this.pr.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPr(String key) {
        return this.pr.getProperty(key);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        ClassLoader loader = ConnectionDemo.class.getClassLoader();
        ConnectionDemo connectionDemo = new ConnectionDemo();
        try (InputStream io = loader.getResourceAsStream("app.properties")) {
            connectionDemo.load(io);
        }
        Class.forName("org.postgresql.Driver");
        String url = connectionDemo.getPr("jdbc.url");
        String login = connectionDemo.getPr("jdbc.login");
        String password = connectionDemo.getPr("jdbc.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
