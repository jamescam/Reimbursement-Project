package com.reimbursement.servlet;

import com.reimbursement.UserRole;
import com.reimbursement.data.EmployeeDAOImpl;
import com.reimbursement.service.LoginValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

//Servlet that handles login requests and calls the isPassword method to validate user credentials.
public class LoginServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String emailParam = request.getParameter("email");
        String passwordParam = request.getParameter("password");

        LoginValidation loginvalidation = new LoginValidation();
        EmployeeDAOImpl employeeDAO = null;

        try {
            boolean isEqual = loginvalidation.isPassword(emailParam, passwordParam, response);

            if(isEqual){
                UserRole employee = UserRole.EMPLOYEE;
                UserRole manager = UserRole.MANAGER;

                employeeDAO = new EmployeeDAOImpl();
                Long UserID = employeeDAO.findUserID(emailParam);
                String email = employeeDAO.findEmail(UserID);
                String userRole = employeeDAO.findUserRole(UserID);

                HttpSession session = request.getSession(true);
                session.setAttribute("User", UserID);
                session.setAttribute("email", email);
                session.setAttribute("userRole", userRole);

                if(session.getAttribute("userRole").equals(employee.toString())){
                    response.sendRedirect("http://localhost:8082/reimbursement/employee");
                }else if(session.getAttribute("userRole").equals(manager.toString())){
                    response.sendRedirect("http://localhost:8082/reimbursement/manhome");
                }
                else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }

            }else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (SQLException|NoSuchAlgorithmException|ClassNotFoundException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if(session.getAttribute("userRole").equals(UserRole.EMPLOYEE.toString())){
            response.sendRedirect("http://localhost:8082/reimbursement/employee");
        }else if(session.getAttribute("userRole").equals(UserRole.MANAGER.toString())){
            response.sendRedirect("http://localhost:8082/reimbursement/manhome");
        }
        else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        }
}
