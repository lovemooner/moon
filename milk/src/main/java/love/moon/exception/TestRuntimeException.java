package love.moon.exception;

import java.io.IOException;

/**
 * Author: lovemooner
 * Date: 2017/5/3 16:34
 */
public class TestRuntimeException {

    public void test1() {
        System.out.println("START =======");
        throw new RuntimeException("test2");
    }

    /**
     * throw了不用return，runtimeException不用在方法签名处显示抛出
     *
     * @return
     */
    public String test2() {
        System.out.println("START =======");
         throw  ExceptionUtil.createException();
    }

    public void returnException(){
        throw new RuntimeException("test2");
    }

    public static void main(String[] args) {
        TestRuntimeException test = new TestRuntimeException();
        test.test1();


    }
}
