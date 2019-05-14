/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tikona.tiny.utilities;

import java.sql.*;
import java.util.ResourceBundle;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

public class LoginConnection {

    static ResourceBundle bundle = ResourceBundle.getBundle("Application");
    private final static String username = bundle.getString("usernameLogin");
    private final static String password = bundle.getString("passwordLogin");
    private final static String hostPort = bundle.getString("hostPortLogin");
    private final static String sid = bundle.getString("SIDLogin");
    private final static String url = "jdbc:oracle:thin:@" + hostPort + ":" + sid;
    public static Connection connection = null;
    public static int connectionCount = 0;
    Logger logger = Logger.getLogger(LoginConnection.class);

    public LoginConnection(boolean setCon) {
        try {
            setConnection();
        } catch (Exception e) {
            logger.error("Exception in Communication Portal Connection:" + e.getMessage());
        }
    }
    public static BasicDataSource dataSource;

    public void setConnection() throws SQLException {
        try {
            if (dataSource == null) {
                dataSource = new BasicDataSource();
                String driver = "oracle.jdbc.OracleDriver";
                try {
                    dataSource.setDriverClassName(driver);
                    dataSource.setUrl(url);
                    dataSource.setUsername(username);
                    dataSource.setPassword(password);
                    dataSource.setInitialSize(10);
                    dataSource.setMaxActive(30);
                    dataSource.setMaxWait(10000);
                    dataSource.setMaxIdle(10);
                    dataSource.setMinIdle(5);
                    dataSource.setValidationQuery("Select 1 From Dual");
                    dataSource.setTestOnBorrow(true);
                    dataSource.setTestOnReturn(true);
                    dataSource.setTestWhileIdle(true);
                    dataSource.setRemoveAbandoned(true);
                    dataSource.setRemoveAbandonedTimeout(180);
                    if (connection == null || connection.isClosed()) {
                        connection = dataSource.getConnection();
                        connectionCount++;
                    }
                } catch (SQLException e) {
                    logger.error("***Connection Requisition*** Could not connect to the Communication Portal database msg :" + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                connection = dataSource.getConnection();
                connectionCount++;
            }
        } catch (Exception e) {
            logger.error("Exception in Communication Portal Connection:" + e.getMessage());
        }
    }

    public static void close(Connection con, Statement stmt, PreparedStatement pstmt, ResultSet res) {
        Logger logger = Logger.getLogger(LoginConnection.class);
        try {

            if (res != null) {
                res.close();
                res = null;
            }

            if (stmt != null) {
                stmt.close();
                stmt = null;
            }

            if (pstmt != null) {
                pstmt.close();
                pstmt = null;
            }

            if (con != null) {
                con.close();
                con = null;
            }

            connectionCount--;

        } catch (Exception e) {
            logger.error("Error in Communication Portal Connection:" + e.getMessage());
        }
    }
}