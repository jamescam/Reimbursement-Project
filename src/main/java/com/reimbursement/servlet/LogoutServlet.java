package com.reimbursement.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//Servlet that handles logout request.
public class LogoutServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
            String relativeHTMLPath = "/WEB-INF/login.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(relativeHTMLPath);
            dispatcher.forward(request, response);
        }
    }
}
