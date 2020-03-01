package love.moon.jdbc;

import java.sql.*;

/**
 * Author: lovemooner
 * Date: 2018/5/29 15:24
 */
public class DBConnection100 {
    private Connection con;         //定义数据库连接类对象
    private PreparedStatement pstm;
    private String user = "root";     //连接数据库用户名
    private String password = "123456";       //连接数据库密码
    private String driverName = "com.mysql.jdbc.Driver";  //数据库驱动
    private String url = "jdbc:mysql://localhost:3306/qingqingtuan";


    /**
     * 创建数据库连接
     */
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("加载数据库驱动失败！");
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url, user, password);     //获取数据库连接
        } catch (SQLException e) {
            System.out.println("创建数据库连接失败！");
            con = null;
            e.printStackTrace();
        }
        return con;                 //返回数据库连接对象
    }


    public static void main(String[] args) {
        Connection mConnection = new DBConnection100().getConnection();
        if (mConnection != null) {
            try {
                String sql = "select * from shop";
                PreparedStatement pstm = mConnection.prepareStatement(sql);
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    //......封装PoPj的操作
                }
                rs.close();
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (mConnection != null) {
                        mConnection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
