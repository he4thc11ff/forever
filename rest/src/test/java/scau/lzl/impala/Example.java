package scau.lzl.impala;

import java.sql.*;

public class Example {

    public static final String DRIVER = "org.apache.hive.jdbc.HiveDriver";
    public static final String URL = "jdbc:hive2://ali2c8g:21050/test233;auth=noSasl";
    public static final String USER = "hive";
    public static final String PASSWORD = "233";

    public static void main(String[] args) throws Exception {
        Class.forName(Example.DRIVER);

        Connection connection = DriverManager.getConnection(Example.URL, Example.USER, Example.PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement("select * from kudu_test");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id"));
        }

        connection.close();
    }
}
