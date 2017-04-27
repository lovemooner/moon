package love.moon.jdbc;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午5:05
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleCheck {

    private static final String URL="jdbc:oracle:thin:@slc01boa.us.oracle.com:1522:slc01boa";
    private static final String NAME="fusion";
    private static final String PASSWORD="fusion";

public static void main(String[]args) throws SQLException{
	Connection ct=null;
	try{
		//1.加载驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //2.得到连接,其中testDemo是配置的数据源的名称,上述的ORCL是数据库的实例
         ct=DriverManager.getConnection(URL, NAME, PASSWORD);
        //从下面开始和sql server一模一样
        Statement sm=ct.createStatement();
//        ResultSet rs=sm.executeQuery("select * from HRH_REMOTE_AGENT_B");  //TODO setMaxResultSet
//        while(rs.next()){
//            System.out.println(rs.getString(2));;
//        }
    }catch(Exception e){
        e.printStackTrace();
    }finally {
		if(ct!=null){
			ct.close();
		}
	}
}
}
