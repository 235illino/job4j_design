

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void execute(String inquiry) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(inquiry);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void createTable(String tableName) {

        execute(String.format(
                "create table if not exists " + tableName + "(%s, %s);",
                "id serial primary key",
                "name varchar(255)"
        ));

    }

    public void dropTable(String tableName) {

        execute("drop table if exists " + tableName);

    }

    public void addColumn(String tableName, String columnName, String type) {

        execute("alter table " + tableName + " add " + columnName + " " + type);

    }

    public void dropColumn(String tableName, String columnName) {

        execute("alter table " + tableName + " drop " + columnName);

    }


    public void renameColumn(String tableName, String columnName, String newColumnName) {

        execute("alter table " + tableName + " rename " + columnName + " " + newColumnName);

    }

    public String getScheme(String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
