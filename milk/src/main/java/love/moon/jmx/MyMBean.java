package love.moon.jmx;


import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.Iterator;
import java.util.Set;

/**
 * Author: lovemooner
 * Date: 2017/6/15 13:45
 */

public class MyMBean {
    public static void main(String[] args) throws Exception {
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8989/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();


        // 对name属性的操作（属性名的第一个字母要大写）
        ObjectName mbeanName = new ObjectName("chengang:name=HelloWorld");
        mbsc.setAttribute(mbeanName, new Attribute("Name", "PANDA"));// 设值
        System.out.println("Name = " + mbsc.getAttribute(mbeanName, "Name"));// 取值
        // 得到proxy代理后直接调用的方式
//        CartController proxy = (CartController) MBeanServerInvocationHandler.newProxyInstance(mbsc, mbeanName, CartController.class, false);
//        proxy.index(null);
        // 远程调用的方式
        mbsc.invoke(mbeanName, "printHello", null, null);
        mbsc.invoke(mbeanName, "printHello", new Object[] { "熊猫烧香" }, new String[] { String.class.getName() });
        // 得mbean的信息
        MBeanInfo info = mbsc.getMBeanInfo(mbeanName);
        System.out.println("Hello Class: " + info.getClassName());
        System.out.println("Hello Attriber：" + info.getAttributes()[0].getName());
        System.out.println("Hello Operation：" + info.getOperations()[0].getName());
        // 得到所有的MBean的ObjectName
        System.out.println("all ObjectName：---------------");
        Set set = mbsc.queryMBeans(null, null);
        for (Iterator it = set.iterator(); it.hasNext();) {
            ObjectInstance oi = (ObjectInstance) it.next();
            System.out.println("\t" + oi.getObjectName());
        }
        // 关闭MBeanServer连接
        jmxc.close();
    }
}