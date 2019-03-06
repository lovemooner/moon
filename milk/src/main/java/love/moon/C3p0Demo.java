package love.moon;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Demo {

    private static Connection conn;
    private static ComboPooledDataSource ds = new ComboPooledDataSource();

    public static Connection getConnection() {
        try {
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://slc11fsp.us.oracle.com:3306/test?useUnicode=true&characterEncoding=UTF8&useServerPrepStmts=true&prepStmtCacheSqlLimit=256&cachePrepStmts=true&prepStmtCacheSize=256&rewriteBatchedStatements=true");
            ds.setUser("root");
            ds.setPassword("123456");
            conn = ds.getConnection();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        getConnection();
    }

}
