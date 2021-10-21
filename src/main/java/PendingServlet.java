import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/*The Servlet that handles the post request triggered by clicking on the accept or reject buttons
 * used to accept or deny an employee's reimbursements.
*/
public class PendingServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(PendingServlet.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userIDParam = req.getParameter("userID");
        String purchaseParam = req.getParameter("purchase");
        String descriptionParam = req.getParameter("description");
        String amountParam = req.getParameter("amount");
        String statusParam = req.getParameter("status");

        HttpSession session = req.getSession(false);
        Object email = session.getAttribute("email");
        Status state;

        // grabs the text value in the accept or reject button.
        if(statusParam.equals("accept")){
            state  = Status.APPROVED;
            try {
                ReimbursementDAOImpl ReimbursementDAO = new ReimbursementDAOImpl();
                PendingDAOImpl PendingDAO = new PendingDAOImpl();
                ArrayList<String> list = PendingDAO.findID(userIDParam);
                ReimbursementDAO.updateApprovedReimbursements(userIDParam, purchaseParam, descriptionParam, amountParam, state.toString(), email.toString());
                for(int i = 0; i < list.size(); i++){
                    Object id = list.get(i);
                    PendingDAO.delete((String) id);

                }

            } catch (SQLException | ClassNotFoundException e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            state = Status.REJECTED;
            try {
                ReimbursementDAOImpl ReimbursementDAO = new ReimbursementDAOImpl();
                PendingDAOImpl PendingDAO = new PendingDAOImpl();
                ArrayList<String> list = PendingDAO.findID(userIDParam);
                ReimbursementDAO.updateRejectedReimbursements(userIDParam, purchaseParam, descriptionParam, amountParam, state.toString(), email.toString());
                for(int i = 0; i < list.size(); i++){
                    Object id = list.get(i);
                    PendingDAO.delete((String) id);
                }
            } catch (SQLException | ClassNotFoundException e) {
                logger.error(e.getMessage(), e);
            }
        }

    }
}
