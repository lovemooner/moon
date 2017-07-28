package love.moon.jdbc.export;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: lovemooner
 * Date: 2017/7/11 11:22
 */
public class DBHelper {
    private static Connection conn;                                        //用于建立数据库连接
    private static PreparedStatement pres;                                        //执行sql的preparedStatement

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");                     //加载驱动类
            String url = "jdbc:oracle:thin:fusion/fusion@slc01boa.us.oracle.com:1522:slc01boa";     //连接数据库的url
            String user = "fusion";
            String pwd = "fusion";
            conn = DriverManager.getConnection(url, user, pwd);                //请求数据库连接
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        writeBlob();
    }

    //写入blob数据
    public static void writeBlob() {
        try {
            conn.setAutoCommit(false);                                     //关闭自动提交
            String sql = "insert into tmp_test values(?,?,?)";               //插入语句

            pres = conn.prepareStatement(sql);
            pres.setInt(1, 1);
            pres.setString(2, "jerry");
            pres.setBlob(3, oracle.sql.BLOB.getEmptyBLOB());                //先插入空的BLOB，获取游标

            pres.executeUpdate();

            sql = "select BDATA from tmp_test where id=?";
            pres = conn.prepareStatement(sql);
            pres.setInt(1, 1);                                             //找出ID为1的，也就是刚刚插入的

            ResultSet res = pres.executeQuery();
            res.next();
            Blob bData = res.getBlob(1);                                 //得到该空的blob

            OutputStream os = bData.setBinaryStream(0);
            os.write(aa.getBytes("UTF-8"));
            os.flush();
            os.close();
            conn.commit();
            conn.setAutoCommit(true);// 恢复现场

            if (res != null)
                res.close();
            if (pres != null)
                pres.close();

            System.out.println("插入成功!!!");
        } catch (SQLException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void getImage() {
        String sql = "select photo from blobtest where id=?";

        try {
            pres = conn.prepareStatement(sql);
            pres.setInt(1, 1);
            ResultSet res = pres.executeQuery();

            while (res.next()) {
                Blob image = res.getBlob(1);                                         //得到该blob

                InputStream is = image.getBinaryStream();                          //获得该blob的输出流
                FileOutputStream fos = new FileOutputStream("E:\\outputImage.jpg");
                int i = 0;
                while ((i = is.read()) != -1) {
                    fos.write(i);
                }
                fos.flush();
                fos.close();
                is.close();
            }
            System.out.println("成功输出图片!!!");
        } catch (SQLException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static String aa="(Updated 9-Jun-17) Re: Question from Steven Miranda \"IF the HCM jobs, for example, don't have any checkpoints, how are they not idempotent?   Either they have checkpoints where they commit along the way OR they commit along the way and don't clean up/restart correct.   I don't understand how you can have a situation w/o save points and then not be idempotent.    I guess possibly, you can commit something small and meaningless at the beginning and then not commit during transactions... but half the HCM jobs set up like that?\"  \n" +
            "Answer: This answer is applicable for all HCM Integration Products processes where we have declared processes as IDEMPOTENT_ALREADY=N and CHECKPOINT_ALREADY=N. Our overall processing of an input file is a series of processes executed in a hierarchy of parent, child and grand child processes. Each individual processes does commit as it goes along. We also save checkpoints when a major phase has been completed. For example, we note when the streaming of the input data file from UCM has been successfully completed. However if any individual ESS processes within the hierarchy was automatically restarted today, they do not have the intelligence to check \"Has the input file already been streamed?\" If yes then skip over that phase. The process always assumes it is executing for the first time and will attempt to stream the file again. In some places there will duplicate key on index errors when second inserts of the same housekeeping rows in our own stage tables are attempted. This will result in the process ending in error and not reaching a normal completion on the second or further execution attempts. As the saved checkpoints are not observed on a second execution we have completed the spreadsheet as CHECKPOINT_ALREADY=N. We need to make code changes so that on later execution attempts phases and steps already completed are not repeated. Our top level process is IDEMPOTENT_ALREADY=Y so a new submission of the same input file would led to the data reaching the primary Fusion product tables without difficulties. As that process is our highest level process any repeats of file streaming into our stage tables will be separated (partitioned) from earlier execution attempts.";
}

