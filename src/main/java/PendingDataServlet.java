import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

//Servlet that handles request to find all pending reimbursements of a single employee.
public class PendingDataServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(PendingDataServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        ObjectMapper objectMapper = new ObjectMapper();
        List list = null;

        try {
            PendingDAOImpl PendingDAO = new PendingDAOImpl();
            list = PendingDAO.findPendingReimbursements(session.getAttribute("User"));
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }

        objectMapper.writeValue(response.getOutputStream(), list);
        PrintWriter pw = response.getWriter();
        pw.write(list.toString());
    }

}
