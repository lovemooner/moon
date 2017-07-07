package love.moon.util;

import javax.management.*;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: lovemooner
 * Date: 2017/6/30 16:31
 */
public class JmxUtil {

    private static String convert(long size) {
        return size / 1024 / 1024 + "M";
    }


    public static void getMemoryMXBean() {
        System.out.println("============================打印整个虚拟机内存使用情况==================");
        MemoryMXBean mm = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = mm.getHeapMemoryUsage();
        printMemoryMXBean(usage);
        MemoryUsage nonUsage = mm.getNonHeapMemoryUsage();
        printMemoryMXBean(nonUsage);

    }

    /**
     * 获取运行时信息
     */
    public static void runtimeMXBean() {
        RuntimeMXBean rmb = (RuntimeMXBean) ManagementFactory.getRuntimeMXBean();
        System.out.println("getClassPath: " + rmb.getClassPath());
        System.out.println("getLibraryPath: " + rmb.getLibraryPath());
        System.out.println("getVmVersion: " + rmb.getVmVersion());
    }

    public static void garbageCollectorMXBean() {
        System.out.println("========================GC的次数以及花费时间之类的信息===============");
        List<GarbageCollectorMXBean> gcmList = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcm : gcmList) {
            System.out.println("getName: " + gcm.getName());
            System.out.println("getMemoryPoolNames " + gcm.getMemoryPoolNames());
        }
    }

    /**
     * 获取多个内存池的使用情况
     */
    public static void memoryPoolMXBean() {
        List<MemoryPoolMXBean> mpmList = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean mpm : mpmList) {
            System.out.println("getUsage: " + mpm.getUsage());
            System.out.println("getMemoryManagerNames: " + mpm.getMemoryManagerNames().toString());
        }
    }



    public static void printThreadMXBean() {
        ThreadMXBean tm = (ThreadMXBean) ManagementFactory.getThreadMXBean();
        System.out.println("getThreadCount " + tm.getThreadCount());
        System.out.println("getPeakThreadCount " + tm.getPeakThreadCount());
        System.out.println("getCurrentThreadCpuTime " + tm.getCurrentThreadCpuTime());
        System.out.println("getDaemonThreadCount " + tm.getDaemonThreadCount());
        System.out.println("getCurrentThreadUserTime " + tm.getCurrentThreadUserTime());
    }

    public static void printOperatingSystemMXBean() {
        OperatingSystemMXBean osm = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
//    System.out.println(osm.getFreeSwapSpaceSize()/1024);
//    System.out.println(osm.getFreePhysicalMemorySize()/1024);
//    System.out.println(osm.getTotalPhysicalMemorySize()/1024);

        //获取操作系统相关信息
        System.out.println("osm.getArch() " + osm.getArch());
        System.out.println("osm.getAvailableProcessors() " + osm.getAvailableProcessors());
        //System.out.println("osm.getCommittedVirtualMemorySize() "+osm.getCommittedVirtualMemorySize());
        System.out.println("osm.getName() " + osm.getName());
        //System.out.println("osm.getProcessCpuTime() "+osm.getProcessCpuTime());
        System.out.println("osm.getVersion() " + osm.getVersion());
    }

    /**
     * 当前编译器情况
     */
    public static void CompilationMXBean() {
        CompilationMXBean gm = (CompilationMXBean) ManagementFactory.getCompilationMXBean();
        System.out.println("getName: " + gm.getName());
        System.out.println("getTotalCompilationTime: " + gm.getTotalCompilationTime());
    }

    public static void printJavaOpts(){
        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        System.out.println("===================java options=============== ");
        System.out.println(inputArguments);
    }

    public static void printMemoryMXBean(MemoryUsage usage) {
        String rate = usage.getUsed() * 100 / usage.getCommitted() + "%";
        System.out.println("usage rate:" + rate
                + ",init=" + convert(usage.getInit())
                + ",used=" + convert(usage.getUsed())
                + ",committed=" + convert(usage.getCommitted())
                + ",max=" + convert(usage.getMax()));
    }



    public static void garbageCollectorMXBean(JMXConnector jmxc) throws MalformedObjectNameException, IntrospectionException, ReflectionException, InstanceNotFoundException, IOException, AttributeNotFoundException, MBeanException {
        System.out.println("\n=========================== garbage Collector ==============================");
        ObjectName obj = new ObjectName("java.lang:type=GarbageCollector,name=PS MarkSweep");
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        ObjectName objname = new ObjectName(obj.getCanonicalName());
        System.out.println("|-PS MarkSweep");
        System.out.println("     |-CollectionCount:" + mbsc.getAttribute(objname, "CollectionCount"));
        System.out.println("     |-CollectionTime:" + mbsc.getAttribute(objname, "CollectionTime")+"ms");

        obj = new ObjectName("java.lang:type=GarbageCollector,name=PS Scavenge");
        objname = new ObjectName(obj.getCanonicalName());
        System.out.println("|-PS Scavenge");
        System.out.println("     |-CollectionCount:" + mbsc.getAttribute(objname, "CollectionCount"));
        System.out.println("     |-CollectionTime:" + mbsc.getAttribute(objname, "CollectionTime"));
    }



    public static void printThreadPool(JMXConnector jmxc) throws IOException, MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException {
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        System.out.println("=========================== Thread Pool ==============================");
        ObjectName obj = new ObjectName("Catalina:type=ThreadPool,name=http-bio-8080");
        System.out.println("|-端口名:" + obj.getKeyProperty("name"));
        ObjectName objname = new ObjectName(obj.getCanonicalName());
        System.out.println("   |-maxThreads:" + mbsc.getAttribute(objname, "maxThreads"));
        System.out.println("   |-currentThreadCount:" + mbsc.getAttribute(objname, "currentThreadCount"));
        System.out.println("   |-currentThreadsBusy:" + mbsc.getAttribute(objname, "currentThreadsBusy"));
        System.out.println("   |-running:" + mbsc.getAttribute(objname, "running"));
        System.out.println("   |-acceptorThreadCount:" + mbsc.getAttribute(objname, "acceptorThreadCount"));
        obj = new ObjectName("Catalina:type=ThreadPool,name=ajp-bio-8009");
        System.out.println("|-端口名:" + obj.getKeyProperty("name"));
        objname = new ObjectName(obj.getCanonicalName());
        System.out.println("   |-maxThreads:" + mbsc.getAttribute(objname, "maxThreads"));
        System.out.println("   |-currentThreadsBusy:" + mbsc.getAttribute(objname, "currentThreadsBusy"));
    }

    public static void printMemoryMXBean(JMXConnector jmxc) throws IOException, MalformedObjectNameException, IntrospectionException, InstanceNotFoundException, ReflectionException, AttributeNotFoundException, MBeanException {
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        ObjectName memoryObj = new ObjectName("java.lang:type=Memory");
        System.out.println("=========================== Memory ==================================");
        System.out.print("|-Heap ");
        MemoryUsage heapUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(memoryObj,
                "HeapMemoryUsage"));
        printMemoryMXBean(heapUsage);
        System.out.print("   |-Eden Space:");
        ObjectName memoryPoolObj = new ObjectName("java.lang:type=MemoryPool,name=PS Eden Space");
        MemoryUsage usage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(memoryPoolObj, "Usage"));
        printMemoryMXBean(usage);
        System.out.print("   |-Survivor Space:");
        memoryPoolObj = new ObjectName("java.lang:type=MemoryPool,name=PS Survivor Space");
        usage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(memoryPoolObj, "Usage"));
        printMemoryMXBean(usage);
        System.out.print("   |-Old Gen:");
        memoryPoolObj = new ObjectName("java.lang:type=MemoryPool,name=PS Old Gen");
        usage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(memoryPoolObj, "Usage"));
        printMemoryMXBean(usage);
        System.out.print("|-noHeap ");
        MemoryUsage nonHeapMemoryUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(memoryObj,
                "NonHeapMemoryUsage"));
        printMemoryMXBean(nonHeapMemoryUsage);

        System.out.print("   |-Perm Gen:");
        memoryPoolObj = new ObjectName("java.lang:type=MemoryPool,name=PS Perm Gen");
        usage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(memoryPoolObj, "Usage"));
        printMemoryMXBean(usage);
        System.out.print("   |-Code Cache:");
        memoryPoolObj = new ObjectName("java.lang:type=MemoryPool,name=Code Cache");
        usage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(memoryPoolObj, "Usage"));
        printMemoryMXBean(usage);
    }




    public static void main(String[] args) throws MalformedObjectNameException, IntrospectionException, IOException, InstanceNotFoundException, AttributeNotFoundException, MBeanException, ReflectionException, InterruptedException {
        String serviceURL = "service:jmx:rmi:///jndi/rmi://slc11fsp.us.oracle.com:8989/jmxrmi";
        JMXServiceURL url = new JMXServiceURL(serviceURL);
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        try {
                JmxUtil.printMemoryMXBean(jmxc);
            JmxUtil.garbageCollectorMXBean(jmxc);
            JmxUtil.printThreadPool(jmxc);
        } finally {
            jmxc.close();
        }
    }
}
