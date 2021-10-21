package com.reimbursement.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursement.data.ReimbursementDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

//Servlet used to provide the client with information of all employees with approved reimbursements.
public class ApprovedReimbursementsDataServlet extends HttpServlet{

        private static final Logger logger = LogManager.getLogger(ApprovedReimbursementsDataServlet.class);

        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

            HttpSession session = request.getSession(false);
            ObjectMapper objectMapper = new ObjectMapper();

            List list = null;

            Object email = session.getAttribute("email");

            try {
                ReimbursementDAOImpl ReimbursementDAO = new ReimbursementDAOImpl();
                list = ReimbursementDAO.findReimbursement(email.toString());
            } catch (SQLException |ClassNotFoundException e) {
                logger.error(e.getMessage(), e);
            }

            objectMapper.writeValue(response.getOutputStream(), list);
            PrintWriter pw = response.getWriter();
            pw.write(list.toString());
            pw.close();
        }
}
