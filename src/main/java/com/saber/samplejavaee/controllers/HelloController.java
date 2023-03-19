package com.saber.samplejavaee.controllers;

import com.saber.samplejavaee.model.query.Person;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

@WebServlet(name = "helloController", urlPatterns = "/hello", displayName = "helloController")
public class HelloController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(HelloController.class.getName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = new Person();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        for (ConstraintViolation<Person> violation : violations) {
            System.out.println(violation);
        }


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
