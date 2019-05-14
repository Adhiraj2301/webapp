/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rahul.yadav
 */
package com.tikona.tiny.Dao;

import com.tikona.tiny.utilities.LoginConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class BatchTest {

    public static void main(String[] args) {

        BatchTest b = new BatchTest();

        b.insertLongUrl("test", "12");
    }
    Logger logger = Logger.getLogger(BatchTest.class);

    public boolean insertLongUrl(String longUrl, String shortUrl) {
        boolean insertStatus = false;
        Connection conUAT;

        final int batchSize = 1000;
        int count = 0;
        PreparedStatement pst = null;
        LoginConnection login = new LoginConnection(true);
        conUAT = LoginConnection.connection;
        String sql = "insert into TINY_URL(LONG_URL,SHORT_URL) values(?,?)";

        try {
            pst = conUAT.prepareStatement(sql);

            for (int i = 0; i < batchSize; i++) {
                pst.setString(1, longUrl);
                pst.setString(2, shortUrl);
                pst.addBatch();
                if (++count % batchSize == 0) {
                    pst.executeBatch();
                    System.out.println("hi");
                }
            }
            pst.executeBatch();
            System.out.println("Record Inserted");
        } catch (Exception e) {
            insertStatus = false;
            System.out.println("" + e);
            logger.error("Exception in insertLongUrl====" + e);

        } finally {
            try {
                conUAT.close();
            } catch (SQLException ex) {
                logger.error("SQLException in insertLongUrl while closing connection====" + ex);
            }
        }
        return insertStatus;
    }
}
