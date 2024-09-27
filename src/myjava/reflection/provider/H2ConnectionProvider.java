package myjava.reflection.provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import myjava.reflection.annotation.Provide;

public class H2ConnectionProvider {

    @Provide
    public Connection buildConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:./data/h2/java_reflection", "sa", "");
    }

}
