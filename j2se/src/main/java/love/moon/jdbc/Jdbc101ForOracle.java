package love.moon.jdbc;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午5:05
 */

import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.*;

public class Jdbc101ForOracle {

    private static final String URL = "jdbc:oracle:thin:@slc01boa.us.oracle.com:1522:slc01boa";
    private static final String NAME = "fusion";
    private static final String PASSWORD = "fusion";

    public Jdbc101ForOracle() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Jdbc101ForOracle checker=new Jdbc101ForOracle();
        checker.validateIsolated();
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

    public void validateIsolated(){
        Connection conn = null;
        Statement stmt=null;
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
