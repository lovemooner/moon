package love.celery.activemq.transport.failover;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午4:24
 */
public interface IConnector {
    void reConnect() throws Exception;
}
