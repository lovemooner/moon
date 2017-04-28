package love.moon.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * User: lovemooner
 * Date: 17-4-18
 * Time: 下午12:46
 */
public class DBConn {
    private static final Logger LOG = LoggerFactory.getLogger(DBConn.class);

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public Connection getConnection() {
        try {
            Class.forName(DataSourceMgr.DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(DataSourceMgr.URL, DataSourceMgr.USERNAME, DataSourceMgr.PASSWORD);
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return conn;
    }


    public void closeConn() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }


    //method3: 专门用于发送增删改语句的方法
    public int execOther(PreparedStatement pstmt) {
        try {
            //1、使用Statement对象发送SQL语句
            int affectedRows = pstmt.executeUpdate();
            //2、返回结果
            return affectedRows;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            return -1;
        }
    }


    //method4: 专门用于发送查询语句
    public ResultSet execQuery(PreparedStatement pstmt) {
        try {
            //1、使用Statement对象发送SQL语句
            rs = pstmt.executeQuery();
            //2、返回结果
            return rs;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }


}
