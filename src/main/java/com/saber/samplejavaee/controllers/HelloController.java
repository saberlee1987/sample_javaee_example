package com.saber.samplejavaee.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "helloController", urlPatterns = "/hello", displayName = "helloController")
public class HelloController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(HelloController.class.getName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("sayHello")) {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                LOG.info(String.format("sayHello call with firstName : %s , lastName : %s ", firstName,lastName));
                String message = String.format("Hello %s %s", firstName, lastName);
                request.setAttribute("message",message);
                request.getRequestDispatcher("/hello.jsp").forward(request, response);
                return;
            } else {
                redirectToHelloPage(request, response);
            }
        }
        redirectToHelloPage(request, response);
    }

    private void redirectToHelloPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/hello.jsp");
    }
}
