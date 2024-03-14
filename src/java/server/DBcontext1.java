/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author admin
 */
public class DBcontext1 {

    private final String serverName = "localhost";
    private final String dbName = "test";
    private final String portNumber = "1433";
    private final String instance = "";
    private final String user = "root";
    private final String pass = "hoancute";

    
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver: //" + serverName + ":" + portNumber + "\\"
                + instance + ";databaseName=" + dbName;
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver: //" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        }
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, pass);
        
    }

}
