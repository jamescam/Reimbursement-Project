package com.reimbursement.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursement.Status;
import com.reimbursement.data.PendingDAOImpl;
import com.reimbursement.data.ReimbursementDAOImpl;
import com.reimbursement.model.PendingReimbursement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/*The Servlet that handles the post request triggered by clicking on the accept or reject buttons
 * used to accept or deny an employee's reimbursements.
*/
public class PendingServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(PendingServlet.class);

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();

        String statusParam = req.getParameter("status");
        String list = req.getParameter("list");

        //Class used by ObjectMapper for deserialization.
        PendingReimbursement pendingreimbursement = mapper.readValue(list, PendingReimbursement.class);

        HttpSession session = req.getSession(false);
        Object email = session.getAttribute("email");
        Status state;

        // grabs the text value in the accept or reject button.
        if(statusParam.equals("accept")){
            state  = Status.APPROVED;
            try {
                ReimbursementDAOImpl ReimbursementDAO = new ReimbursementDAOImpl();
                PendingDAOImpl PendingDAO = new PendingDAOImpl();
                ReimbursementDAO.updateApprovedReimbursements(pendingreimbursement.getPending_id(), pendingreimbursement.getPurchase_date(), pendingreimbursement.getDescription(), pendingreimbursement.getTotal_amount(), state.toString(), email.toString());
                PendingDAO.delete(pendingreimbursement.getID());

            } catch (SQLException | ClassNotFoundException e) {
                logger.error(e.getMessage(), e);
            }
        } else if(statusParam.equals("reject")){
            state = Status.REJECTED;
            try {
                ReimbursementDAOImpl ReimbursementDAO = new ReimbursementDAOImpl();
                PendingDAOImpl PendingDAO = new PendingDAOImpl();
                ReimbursementDAO.updateRejectedReimbursements(pendingreimbursement.getPending_id(), pendingreimbursement.getPurchase_date(), pendingreimbursement.getDescription(), pendingreimbursement.getTotal_amount(), state.toString(), email.toString());
                PendingDAO.delete(pendingreimbursement.getID());

            } catch (SQLException | ClassNotFoundException e) {
                logger.error(e.getMessage(), e);
            }
        }

    }
}
