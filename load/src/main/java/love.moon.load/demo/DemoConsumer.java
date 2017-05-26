package love.moon.load.demo;

import love.moon.load.client.IOClient;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * Author: lovemooner
 * Date: 2017/5/26 14:16
 */
public class DemoConsumer extends AbstractJavaSamplerClient {
    @Override
    public void setupTest(JavaSamplerContext context) {
        super.setupTest(context);
        System.out.println("setupTest");
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sr = new SampleResult();
        try {
            sr.sampleStart();
            IOClient client=new IOClient();
            System.out.println("IOClient:"+client);
            client.connect();
            sr.setResponseData("from Response", null);
            sr.setDataType(SampleResult.TEXT);
            sr.setSuccessful(true);
            sr.sampleEnd();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sr;
    }
    @Override
    public void teardownTest(JavaSamplerContext context) {
        super.teardownTest(context);
    }
}
