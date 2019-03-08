package love.moon.design.proxy.cglib;

import love.moon.design.proxy.jdkProxy.BookFacadeCglib;
import love.moon.design.proxy.jdkProxy.BookFacadeImpl;

public class TestCglib {

    public static void main(String[] args) {
        BookFacadeCglib cglib=new BookFacadeCglib();
        BookFacadeImpl1 bookCglib=(BookFacadeImpl1)cglib.getInstance(new BookFacadeImpl1());
        bookCglib.addBook();
    }
}
