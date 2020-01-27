package love.moon.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: lovemooner
 * Date: 2017/5/22 13:27
 */
public class TransactionTest {


    private Map<String, Connection> connectionMap = new HashMap<String, Connection>();


    public TransactionTest(String URL, String NAME, String PASSWORD) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection queryConnection = DriverManager.getConnection(URL, NAME, PASSWORD);
        Connection updateConnection = DriverManager.getConnection(URL, NAME, PASSWORD);
        queryConnection.setAutoCommit(false);
        updateConnection.setAutoCommit(false);
        connectionMap.put("query", queryConnection);
        connectionMap.put("update", updateConnection);
    }

    private void printData(int id) throws SQLException {
        Connection queryConnection = connectionMap.get("query");
        String updateSQL = "select ID,USER_NAME from ca_user  where ID= ?";
        PreparedStatement pStmt = queryConnection.prepareStatement(updateSQL);
        pStmt.setInt(1, id);
        ResultSet rs = pStmt.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2));
        }
        queryConnection.commit();
    }

    private void printData1(int id) throws SQLException {
        Connection queryConnection = connectionMap.get("query");
        String querySQL = "select ID,USER_NAME from CA_USER  where ID= ?";
        PreparedStatement pStmt = queryConnection.prepareStatement(querySQL);
        pStmt.setInt(1, id);
        ResultSet rs = pStmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2));
        }

        Connection updateConnection =connectionMap.get("update");
        String updateSQL = "update CA_USER set USER_NAME= ? where ID= ?";
        pStmt = updateConnection.prepareStatement(updateSQL);
        pStmt.setString(1, "nan2");
        pStmt.setObject(2, 1000);
        pStmt.executeUpdate();


        queryConnection.commit();
    }


    public void testIsolation() throws Exception {
        int id = 1000;
        Connection updateConnection = null;
        PreparedStatement pStmt = null;
        try {
             updateConnection =connectionMap.get("update");
            updateConnection.setAutoCommit(false);
            String updateSQL = "update ca_user set USER_NAME= ? where ID= ?";
            pStmt = updateConnection.prepareStatement(updateSQL);
            pStmt.setString(1, "nan2");
            pStmt.setObject(2, 1000);
            pStmt.executeUpdate();
            System.out.println("before commit=============");
            printData(id);
            updateConnection.commit();
            System.out.println("after commit=============");
            printData(id);
            pStmt.close();
            close();
        } catch (Exception e) {
            updateConnection.rollback();
            throw e;
        }
    }

    public void close() throws SQLException {
        Connection queryConnection = connectionMap.get("query");
        Connection updateConnection = connectionMap.get("update");
        if (queryConnection != null) {
            queryConnection.close();
        }
        if (updateConnection != null) {
            updateConnection.close();
        }
    }

    public void testUpdate(String URL, String NAME, String PASSWORD) throws Exception {
        Connection conn = null;
        PreparedStatement pStmt = null;
        try {
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
            conn.setAutoCommit(false);
            String updateSQL = "update CA_USER set USER_NAME= ? where ID= ?";
            pStmt = conn.prepareStatement(updateSQL);
            pStmt.setString(1, "nan1");
            pStmt.setObject(2, 1000);
            pStmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (pStmt != null) {
                pStmt.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String URL = "jdbc:mysql://slc11fsp.us.oracle.com:3306/bigdata";
        String NAME = "root";
        String PASSWORD = "123456";
        TransactionTest j = new TransactionTest(URL,NAME,PASSWORD);
        j.testIsolation();
    }

}
