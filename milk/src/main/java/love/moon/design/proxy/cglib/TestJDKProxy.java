package love.moon.design.proxy.cglib;

import love.moon.design.proxy.jdkProxy.BookFacade;
import love.moon.design.proxy.jdkProxy.BookFacadeImpl;
import love.moon.design.proxy.jdkProxy.BookFacadeProxy;

public class TestJDKProxy {

    public static void main(String[] args) {
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
        bookProxy.addBook();
    }

}
