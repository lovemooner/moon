package love.moon.exception;

import love.moon.common.exception.MyException;
import love.moon.common.exception.MyExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试方法里是否要catch异常，日志显示的区别
 * Author: lovemooner
 * Date: 2017/5/3 14:53
 */
public class Demo implements IDemo{
    private static final Logger LOG = LoggerFactory.getLogger(Demo.class);
    public void test1() throws Exception {
     int a;
     String b;
     if(1==1){
         try {
             throw new MyException(MyExceptionType.UserNotFound);
         } catch (MyException e) {
//             LOG.error(e.getMessage(),e);
             throw e;
         }
     }
     System.out.println("Finish");
    }
 public static void main(String[]args)  {
     Demo demo=new Demo();
     try {
         demo.test1();
     } catch (MyException e) {
         LOG.info(e.getType().toString());
         LOG.error(e.getMessage(),e);
    } catch (Exception e) {
        LOG.error(e.getMessage(),e);
    }
 }

}
