package com.reimbursement.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//Servlet used to display data on the employee's profile page.
public class ProfileDataServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ObjectMapper objectMapper = new ObjectMapper();
        HttpSession session = request.getSession(false);

        Object email = session.getAttribute("email");
        Object UserID = session.getAttribute("User");
        Object userRole = session.getAttribute("userRole");

        List<Map<Object, Object>> list = new ArrayList<>();
        Map map = new TreeMap();
        map.put("email", email);
        map.put("UserID", UserID);
        map.put("userRole", userRole);
        list.add(map);
        objectMapper.writeValue(response.getOutputStream(), list);
        PrintWriter pw = response.getWriter();
        pw.write(list.toString());
        pw.close();
    }
}
