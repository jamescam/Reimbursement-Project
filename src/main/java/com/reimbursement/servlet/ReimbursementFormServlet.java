package com.reimbursement.servlet;

import com.reimbursement.data.PendingDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

//Servlet that grabs information from the reimbursement form.
public class ReimbursementFormServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(ReimbursementFormServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

        PendingDAOImpl PendingDAO = new PendingDAOImpl();

        String UserIDParam = request.getParameter("userID");
        String purchaseParam = request.getParameter("purchase");
        String descriptionParam = request.getParameter("description");
        String TotalParam = request.getParameter("total");
        String status = "PENDING";

        try {
            PendingDAO.update(UserIDParam, purchaseParam, descriptionParam,TotalParam, status);
        } catch (SQLException |ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }

    }

}
