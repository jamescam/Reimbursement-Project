import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/* A servlet class that handles a get request
 * in the form of a button click to
 * retrieve all rejected reimbursements.
 */

public class FindAllRejectServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(FindAllRejectServlet.class);


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        // list assigned to a dummy value, so it can be used outside the try-catch block.
        List list = null;

        try {
            ReimbursementDAOImpl reimbursementDAO = new ReimbursementDAOImpl();

            //Returns a Tree Map  inside a list filled with all the info from rejected reimbursements.
            list = reimbursementDAO.findAllRejectedReimbursements();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }

        //converts a Java object into Json object and prints the list to the response body as a string.
        objectMapper.writeValue(response.getOutputStream(), list);
        PrintWriter pw = response.getWriter();
        pw.write(list.toString());

    }
}
