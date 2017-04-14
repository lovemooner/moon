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

public class DBCheck {

public static void main(String[]args) throws SQLException{
	Connection ct=null;
	try{
		//1.加载驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //2.得到连接,其中testDemo是配置的数据源的名称,上述的ORCL是数据库的实例
         ct=DriverManager.getConnection("jdbc:oracle:thin:@slcak354.us.oracle.com:1600:ems16679", "fusion", "fusion");
        //从下面开始和sql server一模一样
        Statement sm=ct.createStatement();
        ResultSet rs=sm.executeQuery("select * from HRH_REMOTE_AGENT_B");
        while(rs.next()){
            System.out.println(rs.getString(2));;
        }
    }catch(Exception e){
        e.printStackTrace();
    }finally {
		if(ct!=null){
			ct.close();
		}
	}
}
}
