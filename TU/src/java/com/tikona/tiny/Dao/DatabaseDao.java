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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;

public class DatabaseDao {

    Logger logger = Logger.getLogger(DatabaseDao.class);

    public boolean insertLongUrl(String longUrl, String shortUrl, int ttl,String createdBy,String source) {

        logger.info("add Expired Date for URL");


        String expDate = "";
        int TTL;

        TTL = ttl;
       

        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yy");
        Calendar c = Calendar.getInstance();
        if (TTL <= 0) {

            c.add(Calendar.YEAR, 3);
            c.add(Calendar.MONTH, 0);
            c.add(Calendar.DATE, 0);

        } else {

            c.add(Calendar.YEAR, 0);
            c.add(Calendar.MONTH, 0);
            c.add(Calendar.DATE, TTL);

        }

        Date day3 = c.getTime();

        expDate = sd.format(day3);
        System.out.println("n+ day expired Date)" + sd.format(day3));
        logger.info("Expired date 4 URL " + longUrl + " " + expDate);



        boolean insertStatus = false;
        Connection conUAT;

        PreparedStatement pst = null;
        LoginConnection login = new LoginConnection(true);
        conUAT = login.connection;

        logger.info("Dao insertLongUrl()" + longUrl + " short " + shortUrl);
        try {
            pst = conUAT.prepareStatement("insert into TINY_URL(ORIGINAL_URL,SHORT_URL,CREATED_DATE,CREATED_BY,LAST_ACCESED_ON,TTL,EXP_DATE,SOURCE) values(?,?,sysdate,?,TO_DATE(?,'dd-MM-yy'),?,TO_DATE(?,'dd-MM-yy'),?)");
            pst.setString(1, longUrl);
            pst.setString(2, shortUrl);
            pst.setString(3, createdBy);
            pst.setString(4, null);
            pst.setString(5, String.valueOf(TTL));
            pst.setString(6, expDate);
            pst.setString(7, source);
            int i = pst.executeUpdate();
            if (i > 0) {
                insertStatus = true;

                logger.info("Record Inserted ");
            }

        } catch (Exception e) {
           
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

    public String retriveLongUrl(String short_url) throws SQLException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
        Date date = new Date();
        String todayDate = formatter.format(date);

        String retriveUrl = "";
        Connection conUAT;

        PreparedStatement pst = null;
        ResultSet rs = null;
        LoginConnection login = new LoginConnection(true);
        conUAT = LoginConnection.connection;
        conUAT.setAutoCommit(false);

        logger.info("Dao  retriveLongUrl()" + " short Url " + short_url + " today Date " + todayDate);
        try {
            pst = conUAT.prepareStatement("SELECT * FROM TINY_URL where SHORT_URL=? AND EXP_DATE>=TO_DATE(?,'dd-MM-yy')");
            pst.setString(1, short_url);
            pst.setString(2, todayDate);

            rs = pst.executeQuery();
            if (rs.next()) {
                retriveUrl = rs.getString("ORIGINAL_URL");
                logger.info("Retrive long url from DB " + retriveUrl);
            }

            updateLastAccesedDate(short_url);
            conUAT.commit();

        } catch (Exception e) {

            System.out.println("" + e);
            logger.error("Exception in retriveLongUrl====" + e);

        } finally {
            try {
                conUAT.close();
            } catch (SQLException ex) {
                logger.error("SQLException in retriveLongUrl while closing connection====" + ex);
            }
        }
        return retriveUrl;
    }

    public String checkUrlFirst(String url) {

         SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy");
        Date date = new Date();
        String todayDate = formatter.format(date);

        String dataExit = "";
        Connection conUAT;

         logger.info("checkUrlFirst ");
        PreparedStatement pst = null;
        ResultSet rs = null,rs2=null;
        LoginConnection login = new LoginConnection(true);
        conUAT = LoginConnection.connection;
        logger.info("Dao checkUlrFirst()" + " Original Url " + url);
        try {
            pst = conUAT.prepareStatement("SELECT * FROM TINY_URL where ORIGINAL_URL=? AND EXP_DATE>=TO_DATE(?,'dd-MM-yy')  ");
            pst.setString(1, url);
            pst.setString(2,todayDate);
           

            rs = pst.executeQuery();
            if (rs.next()) {
                dataExit = rs.getString("SHORT_URL");
                System.out.println("SHORT URL " + dataExit);
                logger.info("Record Availble " + dataExit);
            } 


        } catch (Exception e) {

            System.out.println("" + e);
            logger.error("Exception in checkUrlFirst====" + e);

        } finally {
            try {
                conUAT.close();
            } catch (SQLException ex) {
                logger.error("SQLException in retriveLongUrl while closing connection====" + ex);
            }
        }
        return dataExit;
    }

    public boolean checkShortUrl(String result) {

        
        boolean dataExit = false;
        Connection conUAT;

        PreparedStatement pst = null;
        ResultSet rs = null,rs2=null;
        LoginConnection login = new LoginConnection(true);
        conUAT = LoginConnection.connection;

        logger.info("Dao checkShortUrl() Short Url " + result);
        try {
            pst = conUAT.prepareStatement("SELECT * FROM TINY_URL where SHORT_URL=? ");
            pst.setString(1, result);
           

            rs = pst.executeQuery();
            if (rs.next()) {
               dataExit=true;
                System.out.println("SHORT URL " + dataExit);
                logger.info("Record Availble " + dataExit);
            } 


        } catch (Exception e) {

            System.out.println("" + e);
            logger.error("Exception in checkShortUrl====" + e);

        } finally {
            try {
                conUAT.close();
            } catch (SQLException ex) {
                logger.error("SQLException in checkShortUrl while closing connection====" + ex);
            }
        }
        return dataExit;
    }

    public void updateLastAccesedDate(String short_url) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy'T'HH:mm:ss");
        Date date = new Date();
        String todayDate = formatter.format(date);


        Connection conUAT;

        PreparedStatement pst = null;
        int i = 0;
        LoginConnection login = new LoginConnection(true);
        conUAT = LoginConnection.connection;

        logger.info("Dao updateLastAccesedDate()" + " Short Url " + short_url);
        try {
            pst = conUAT.prepareStatement("update  TINY_URL set LAST_ACCESED_ON=? WHERE SHORT_URL=? ");
            pst.setString(1, todayDate);
            pst.setString(2, short_url);

            i = pst.executeUpdate();


        } catch (Exception e) {

            System.out.println("" + e);
            logger.error("Exception in updateLastAccesedDate====" + e);

        } finally {
            try {
                conUAT.close();
            } catch (SQLException ex) {
                logger.error("SQLException in updateLastAccesedDate while closing connection====" + ex);
            }
        }

    }
}
