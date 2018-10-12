package love.moon.jdbc.template;

import love.moon.jdbc.tmp.DataSourceMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class JdbcTemplate {
    private static final Logger LOG = LoggerFactory.getLogger(JdbcTemplate.class);

    private Connection conn;
    private PreparedStatement pStmt;


    public JdbcTemplate(String url,String user,String pass){
        try {
            Class.forName(DataSourceMgr.DRIVER_CLASS_NAME);
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void execute(DataManager dm) {
        try {

            dm.manageData();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);

        } finally {
            close(pStmt, conn);
        }
    }

    /**
     * 关闭 → 注意关闭的顺序 rs,sta,con.
     *
     * @param rs
     * @param sta
     * @param con
     */
    public void close(ResultSet rs, PreparedStatement sta, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (sta != null) {
                    sta.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * sta,con的关闭
     *
     * @param sta
     * @param con
     */
    public void close(PreparedStatement sta, Connection con) {
        this.close(null, sta, con);
    }

    public void main(String[] args) {
        new JdbcTemplate(null,null,null).execute(new DataManager() {
            public void manageData() throws SQLException {
                String sql = "select * from user";
                pStmt=conn.prepareStatement(sql);
                ResultSet rs = pStmt.executeQuery();
                    while (rs.next()) {
                        System.out.println(rs.getObject(0));
                    }
            }
        });

    }

}
