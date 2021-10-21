import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//Servlet used forward to the employee's homepage.
public class RedirectServlet extends HttpServlet {

        @Override
        public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(false);

            if (session == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }else{
                String relativeHTMLPath = "/WEB-INF/employee.html";
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(relativeHTMLPath);
                dispatcher.forward(request, response);
            }
        }
    }
