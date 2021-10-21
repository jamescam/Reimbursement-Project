import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EmployeeTableServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false); //gets the session of the user. Returns if a session doesn't exist.

        /*If there is no user session, then it outputs a 401 status code.
         If there is a user session, it forwards the page to the relative HTML path.*/

        if (session == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            String relativeHTMLPath = "/WEB-INF/employeetable.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(relativeHTMLPath);
            dispatcher.forward(request, response);
        }
    }
}
