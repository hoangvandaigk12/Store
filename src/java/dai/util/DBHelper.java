/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dai.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author AD
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection() throws NamingException, SQLException {
//        //1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create Connection
//        String sql = "jdbc:sqlserver://localhost:1433;databaseName=FU";
//        //3. Open connection
//        Connection con = DriverManager.getConnection(sql, "sa", "21031999");
//        return con;
        Context currentContext = new InitialContext();
        Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
        DataSource ds = (DataSource)tomcatContext.lookup("Filter");
        Connection con = ds.getConnection();
        return con;
    }
}
