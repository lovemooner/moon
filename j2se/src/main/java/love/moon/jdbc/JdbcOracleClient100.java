package love.moon.jdbc;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午5:05
 */

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class JdbcOracleClient100 {

    private static final String URL = "jdbc:oracle:thin:@10.68.16.44:1521:ORCL";
    private static final String NAME = "mp_bask_app";
    private static final String PASSWORD = "mp_bask_app";

    public JdbcOracleClient100() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        JdbcOracleClient100 client = new JdbcOracleClient100();
        client.select();
//        client.validateIsolated();
    }


    @SneakyThrows
    public void select() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        long start=System.currentTimeMillis();
        try {
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
            stmt = conn.createStatement();
            stmt.setFetchSize(1000000);
            rs = stmt.executeQuery("select * from mp_ydjw_app.t_ydjw_app_user_temp");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " "
                        + " " + rs.getString(2)
                        + " " + rs.getString(3)
                        + " " + rs.getString(4)
                        + " " + rs.getString(5));
            }
           log.info("elapsed:{}",System.currentTimeMillis()-start);
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

//    public void update(){
//        Connection connection = null;
//        Statement stmt=null;
//        try {
//            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
//            connection.setAutoCommit(false);
//            connection.setReadOnly(true);
//            stmt = connection.createStatement();
//            printData(stmt);
//            System.out.println("=========Validate ORACLE Isolate:READ COMMITED=========");
//            printData(stmt);
//            connection.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (stmt != null) {
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//    }

    public void validateIsolated() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(URL, NAME, PASSWORD);
            conn.setAutoCommit(false);
            conn.setReadOnly(true);
            PreparedStatement ps = conn.prepareStatement("set transaction read only");
            ps.execute();
            stmt = conn.createStatement();
            printData(stmt);
            System.out.println("=========Validate ORACLE Isolate:READ COMMITED=========");
            printData(stmt);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    private static void printData(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("select * from NAN_STUDENT where id=5");  //TODO setMaxResultSet
        while (rs.next()) {
            System.out.print(rs.getString(1) + " ");
            System.out.print(rs.getString(2) + " ");
            System.out.println(rs.getString(3));
        }
    }

}
