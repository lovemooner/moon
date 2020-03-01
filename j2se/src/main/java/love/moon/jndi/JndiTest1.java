package love.moon.jndi;

import java.io.FileInputStream;
import java.util.Properties;
import javax.naming.*;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Author: lovemooner
 * Date: 2017/5/5 17:15
 */
public class JndiTest1 {
    /**
     *
     */
    public JndiTest1() {
        super();
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        try {
            Properties env = new Properties();
            env.load(new FileInputStream("D:\\moon\\milk\\src\\main\\java\\love\\moon\\jndi\\fileSystemService.properties"));
            env.put(Context.PROVIDER_URL, "file:///c:/");
            Context ctx = new InitialContext(env);
            ctx.createSubcontext("sylilzy");

            NamingEnumeration list = ctx.list("/");
            while (list.hasMore()) {
                NameClassPair nc = (NameClassPair) list.next();
                System.out.println(nc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
