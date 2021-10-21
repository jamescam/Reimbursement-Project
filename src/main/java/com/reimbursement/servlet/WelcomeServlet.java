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

//Servlet that displays a user's email next to the welcome message in the user's homepage.
public class WelcomeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        ObjectMapper objectMapper = new ObjectMapper();

        Object email = session.getAttribute("email");

        List<Map<Object, Object>> list = new ArrayList<>();
        Map map = new TreeMap();
        map.put("email", email);
        list.add(map);
        objectMapper.writeValue(response.getOutputStream(), list);
        PrintWriter pw = response.getWriter();
        pw.write(list.toString());
        pw.close();
    }
}
