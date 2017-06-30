package love.moon.util;

import java.lang.management.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: lovemooner
 * Date: 2017/6/30 16:31
 */
public class JmxUtil {

    private static String convert(long size){
        return size/1024/1024+"M";
    }


    /**
     * 获取运行时信息
     */
    public static void runtimeMXBean(){
        RuntimeMXBean rmb = (RuntimeMXBean) ManagementFactory.getRuntimeMXBean();
        System.out.println("getClassPath: " + rmb.getClassPath());
        System.out.println("getLibraryPath: " + rmb.getLibraryPath());
        System.out.println("getVmVersion: " + rmb.getVmVersion());
    }
    public static void garbageCollectorMXBean(){
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
    public static void memoryPoolMXBean(){
        List<MemoryPoolMXBean> mpmList = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean mpm : mpmList) {
            System.out.println("getUsage: " + mpm.getUsage());
            System.out.println("getMemoryManagerNames: " + mpm.getMemoryManagerNames().toString());
        }
    }

    public static void getMemoryMXBean(){
        System.out.println("============================打印整个虚拟机内存使用情况==================");
        MemoryMXBean mm = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = mm.getHeapMemoryUsage();
        System.out.println("HeapMemoryUsage init=" + convert(usage.getInit())+" USED HEAP="+convert(usage.getUsed())+
                " committed="+convert(usage.getCommitted())+" max="+convert(usage.getMax()));
        MemoryUsage nonUsage= mm.getNonHeapMemoryUsage();
        System.out.println("NonHeapMemoryUsage init=" + convert(nonUsage.getInit())+" USED HEAP="+convert(nonUsage.getUsed())+
                " committed="+convert(nonUsage.getCommitted())+" max="+convert(nonUsage.getMax()));
    }

    public static void printThreadMXBean(){
        ThreadMXBean tm = (ThreadMXBean) ManagementFactory.getThreadMXBean();
        System.out.println("getThreadCount " + tm.getThreadCount());
        System.out.println("getPeakThreadCount " + tm.getPeakThreadCount());
        System.out.println("getCurrentThreadCpuTime " + tm.getCurrentThreadCpuTime());
        System.out.println("getDaemonThreadCount " + tm.getDaemonThreadCount());
        System.out.println("getCurrentThreadUserTime " + tm.getCurrentThreadUserTime());
    }

    public static void printOperatingSystemMXBean(){
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
    public static void CompilationMXBean(){
        CompilationMXBean gm = (CompilationMXBean) ManagementFactory.getCompilationMXBean();
        System.out.println("getName: " + gm.getName());
        System.out.println("getTotalCompilationTime: " + gm.getTotalCompilationTime());
    }

    public static void main(String[] args) {
        JmxUtil.getMemoryMXBean();
        final AtomicInteger count = new AtomicInteger();
        while (true) {
            new Thread(new Runnable() {
                public void run() {
                    System.out.println(count.incrementAndGet());
                    while (true)
                        try {
                            Thread.sleep(Integer.MAX_VALUE);
                        } catch (InterruptedException e) {
                            break;
                        }
                }
            }).start();
        }
    }
}
