package love.moon.load.jload.monitor;

import love.moon.load.jload.bean.Config;
import love.moon.load.jload.bean.JLoads;
import love.moon.load.jload.bean.LoadCache;

import java.text.DecimalFormat;

/**
 * Author: lovemooner
 * Date: 2018/8/23 13:18
 */
public class SummaryMonitor implements IMonitor {

    private long start;
    private long throughput;


    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }




    public long getThroughput() {
        return throughput;
    }

    public void setThroughput(long throughput) {
        this.throughput = throughput;
    }

    private String padding(String str, int targetLen) {
        return str;
    }

    private void prettyPrint(long average) {
        LoadCache cache = JLoads.getLoadCache();
        long samples = cache.getSamples();
        String tSamples = " samples ";
        String tAverage = " average ";
        System.out.println("+------------------------------------------------------------+");
        System.out.println("|" + tSamples + "|" + tAverage + "| Min | Max | throughput | Received KB/s |");
        System.out.println("|" + padding(String.valueOf(samples), tSamples.length()) + "|" + average + "|" + cache.getMinResponseTime() + "|" + cache.getMaxResponseTime() + "|" + throughput + "|" + "    " + "|");
        System.out.println("+------------------------------------------------------------+");
    }


    public void start() {
        setStart(System.currentTimeMillis());
        LoadCache cache = JLoads.getLoadCache();
        while (cache.getSamples()==0){
//            System.out.println("monitoring...");
            try {
                Thread.sleep(Config.MONITOR_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        StringBuilder sb=new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.00");
        while (true) {
            long samples = cache.getSamples();
            long startS = (System.currentTimeMillis() - start) /1000 +1 ;
            throughput = samples / startS;
            long totalResponseTime = cache.getTotalResponseTime();
            long average = totalResponseTime / samples;
            sb.append ("Samples:" ).append(samples).append(",finish:").append(totalResponseTime / 1000).append(" s,Average:").append(average)
                    .append(" ms,Min:").append(cache.getMinResponseTime()).append(" ms,Max:").append(cache.getMaxResponseTime() )
                    .append(" ms,Throughput:").append(throughput).append("/s,Error:").append(df.format((float)cache.getErrorSamples()*1000/samples)).append("‰");
            System.out.println(sb.toString());
            sb.delete( 0, sb.length() );
            try {
                Thread.sleep(Config.MONITOR_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
