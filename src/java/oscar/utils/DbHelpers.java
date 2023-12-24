/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscar.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Oscar
 */
public class DbHelpers {
        
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "12345";
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=BookStoreManagement";
    
    public static final Connection getConnection()
            throws ClassNotFoundException, SQLException{
        Connection con = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection(DB_URL, DB_PASSWORD, DB_PASSWORD);
        return con;
    }
    
    public static void closeConnection(ResultSet rs, PreparedStatement stm,
            Connection con) throws SQLException{
        if(rs != null) rs.close();
        if(stm != null) stm.close();
        if(con != null) con.close();
    }
    
    public static void closeConnection(PreparedStatement stm, Connection con)
            throws SQLException{
        if(stm != null) stm.close();
        if(con != null) con.close();
    }
}
