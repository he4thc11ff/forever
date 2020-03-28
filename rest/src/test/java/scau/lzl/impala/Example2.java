package scau.lzl.impala;

import java.sql.*;

public class Example2 {

    public static void main(String[] args) throws Exception {
        Class.forName(Example.DRIVER);

        Connection connection = DriverManager.getConnection(Example.URL, "moon", "");

        Statement statement = connection.createStatement();

        statement.execute("create table kudu_test4(id Int, name String, primary key (id)) stored as kudu");

        connection.close();
    }
}
