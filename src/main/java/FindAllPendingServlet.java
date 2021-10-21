import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

        // Servlet that handles requests to retrieve all pending reimbursements.
public class FindAllPendingServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(FindAllPendingServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ObjectMapper objectMapper = new ObjectMapper();
        List list = null;

        try {
            PendingDAOImpl PendingDAO = new PendingDAOImpl();
            list = PendingDAO.findAllPendingReimbursements();
        }catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }

        objectMapper.writeValue(response.getOutputStream(), list);
        PrintWriter pw = response.getWriter();
        pw.write(list.toString());
    }
}
