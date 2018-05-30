package love.moon.jdbc;

import java.sql.*;

/**
 * Author: lovemooner
 * Date: 2017/5/22 13:27
 */
public class Jdbc100 {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String NAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from CA_USER");  //TODO setMaxResultSet
            while (rs.next()) {
                System.out.print(rs.getString(1) + " ");
                System.out.println(rs.getString(2));
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
