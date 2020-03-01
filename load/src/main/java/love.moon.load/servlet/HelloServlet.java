package love.moon.load.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Author: lovemooner
 * Date: 2017/6/13 17:46
 */
public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        System.out.print("Hello Servlet");

        response.getWriter().println("Hello Servlet");
    }
}