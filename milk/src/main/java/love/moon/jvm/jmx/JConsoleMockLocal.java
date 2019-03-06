package love.moon.jvm.jmx;

import java.lang.management.*;
import java.util.List;

/**
 * Author: lovemooner
 * Date: 2017/6/15 13:02
 */
public class JConsoleMockLocal {

    private static String convert(long size){
        return size/1024/1024+"M";
    }

    public static void main(String[] args) {

        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        System.out.println("===================java options=============== ");
        System.out.println(inputArguments);


        System.out.println("=======================通过java来       获取相关系统状态============================ ");
        int i = (int) Runtime.getRuntime().totalMemory() / 1024;//Java 虚拟机中的内存总量,以字节为单位
        System.out.println("总的内存量 i is " + i);
        int j = (int) Runtime.getRuntime().freeMemory() / 1024;//Java 虚拟机中的空闲内存量
        System.out.println("空闲内存量 j is " + j);
        System.out.println("最大内存量 is " + Runtime.getRuntime().maxMemory() / 1024);

        System.out.println("=======================OperatingSystemMXBean============================ ");

        //获取整个虚拟机内存使用情况

        //获取各个线程的各种状态，CPU 占用情况，以及整个系统中的线程状况







    }
}
