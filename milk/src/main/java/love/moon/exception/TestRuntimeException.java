package love.moon.exception;

import java.io.IOException;

/**
 * Author: lovemooner
 * Date: 2017/5/3 16:34
 */
public class TestRuntimeException {

    public void test1() throws IOException {

        try {
            System.out.println("START =======");
            if (1 == 1)
                throw new NullPointerException("test1");
            System.out.println("cen =======");
            throw new IOException();
        } catch (IOException e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        TestRuntimeException test = new TestRuntimeException();
        try {
            test.test1();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}