package love.moon.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Author: lovemooner
 * Date: 2017/10/10 10:39
 */
public class SessionManager extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("userName","nan");
        System.out.println(session.getAttribute("session"));
    }
}
