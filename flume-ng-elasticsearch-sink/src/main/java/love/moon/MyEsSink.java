package love.moon;
/**
 * @auther dongnan
 * @date 2019/9/22 1:05
 * @describe
 */

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.flume.sink.elasticsearch.ElasticSearchSinkConstants.*;

public class MyEsSink extends AbstractSink implements Configurable {
    private String hostNames;
    private String indexName;
    private String clusterName;
    static TransportClient client;

    public void configure(Context context) {
        hostNames = context.getString(HOSTNAMES);
        indexName = context.getString(INDEX_NAME);
        clusterName = context.getString(CLUSTER_NAME);
    }

    @Override
    public void start() {
        Settings settings = Settings.builder().put("cluster.name", clusterName).build();
        try {
            client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(
                    InetAddress.getByName(hostNames.split(":")[0]), Integer.parseInt(hostNames.split(":")[1])));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {
        super.stop();
    }

    public Status process() throws EventDeliveryException {
        Status status = null;
        Channel ch = getChannel();
        Transaction txn = ch.getTransaction();
        txn.begin();
        try {
            Event event = ch.take();
            Map<String, String> head = event.getHeaders();
            Map<String, Object> map = new HashMap<String, Object>();

            for (String key : head.keySet()) {
                map.put("topic", key);
                map.put("timestamp", head.get(key));
                map.put("data", new String(event.getBody()));
            }

            IndexRequestBuilder create = client.prepareIndex(indexName, "text").setSource(map);
            IndexResponse response = create.execute().actionGet();

            txn.commit();
            status = Status.READY;
        } catch (Throwable t) {
            txn.rollback();
            status = Status.BACKOFF;
            if (t instanceof Error) {
                throw (Error) t;
            }
        } finally {
            txn.close();
        }
        return status;
    }
}