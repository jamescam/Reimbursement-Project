package com.reimbursement.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*Servlet that forwards to the reimbursement table
 * that show the employee's their approved or rejected Reimbursements
 */
public class ReimbursementServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(ReimbursementServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            String relativeHTMLPath = "/WEB-INF/reimbursement.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(relativeHTMLPath);
            dispatcher.forward(request, response);
        }
    }

}
