package love.moon.jvm.jmx;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Formatter;
import java.util.Set;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

/**
 * Author: lovemooner
 * Date: 2017/6/15 13:36
 */
public class JConsoleMockRemote {

    private static String convert(long size){
        return size/1024/1024+"M";
    }

    public static void main(String[] args) {
        try {

            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://slc11fsp.us.oracle.com:8989/jmxrmi");
            JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

            //端口最好是动态取得
            ObjectName threadObjName = new ObjectName("Catalina:type=ThreadPool,name=http-8080");
            MBeanInfo mbInfo = mbsc.getMBeanInfo(threadObjName);

            String attrName = "currentThreadCount";//tomcat的线程数对应的属性值
            MBeanAttributeInfo[] mbAttributes = mbInfo.getAttributes();
            System.out.println("currentThreadCount:" + mbsc.getAttribute(threadObjName, attrName));


            System.out.println("====================Session===========================");
            ObjectName managerObjName = new ObjectName("Catalina:type=Manager,*");
            Set<ObjectName> s = mbsc.queryNames(managerObjName, null);
            for (ObjectName obj : s) {
                System.out.println("应用名:" + obj.getKeyProperty("path"));
                ObjectName objname = new ObjectName(obj.getCanonicalName());
                System.out.println("最大会话数:" + mbsc.getAttribute(objname, "maxActiveSessions")
                        +" 会话数:" + mbsc.getAttribute(objname, "activeSessions")
                        +" 活动会话数:" + mbsc.getAttribute(objname, "sessionCounter"));
            }



            System.out.println("====================system===========================");
            ObjectName runtimeObjName = new ObjectName("java.lang:type=Runtime");
            System.out.println("厂商:" + (String) mbsc.getAttribute(runtimeObjName, "VmVendor"));
            System.out.println("程序:" + (String) mbsc.getAttribute(runtimeObjName, "VmName"));
            System.out.println("版本:" + (String) mbsc.getAttribute(runtimeObjName, "VmVersion"));
            Date starttime = new Date((Long) mbsc.getAttribute(runtimeObjName, "StartTime"));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("启动时间:" + df.format(starttime));

            Long timespan = (Long) mbsc.getAttribute(runtimeObjName, "Uptime");
            System.out.println("连续工作时间:" + formatTimeSpan(timespan));
            Set MBeanset = mbsc.queryMBeans(null, null);
            System.out.println("MBeanset.size() : " + MBeanset.size());
           //MBean
            for (int j = 0; j < mbsc.getDomains().length; j++) {
                System.out.println("####" + mbsc.getDomains()[j]);
            }
//            // MBean的总数
//            System.out.println("MBean count = " + mbsc.getMBeanCount());
//            Iterator MBeansetIterator = MBeanset.iterator();
//            while (MBeansetIterator.hasNext()) {
//                ObjectInstance objectInstance = (ObjectInstance) MBeansetIterator.next();
//                ObjectName objectName = objectInstance.getObjectName();
//                String canonicalName = objectName.getCanonicalName();
//                System.out.println("canonicalName : " + canonicalName);
//                if (canonicalName.equals("Catalina:host=localhost,type=Cluster")) {
//                    // Get details of cluster MBeans
//                    System.out.println("Cluster MBeans Details:");
//                    System.out.println("=========================================");
//                    //getMBeansDetails(canonicalName);
//                    String canonicalKeyPropList = objectName.getCanonicalKeyPropertyListString();
//                }
//            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String formatTimeSpan(long span) {
        long minseconds = span % 1000;

        span = span / 1000;
        long seconds = span % 60;

        span = span / 60;
        long mins = span % 60;

        span = span / 60;
        long hours = span % 24;

        span = span / 24;
        long days = span;
        return (new Formatter()).format("%1$d天 %2$02d:%3$02d:%4$02d.%5$03d", days, hours, mins, seconds, minseconds)
                .toString();
    }
}
