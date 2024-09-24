package myjava.reflection.db;

import java.sql.SQLException;

import org.h2.tools.Server;

public class DBLauncher {

    public static void main(String[] args) throws SQLException {
        // Start H2 db with base directory ./data/h2
        Server.main(new String[]{"-baseDir", "./", "-tcpAllowOthers"});
        System.out.println("DB launched. JDBC URL: jdbc:h2:./java_reflection");
    }

}
