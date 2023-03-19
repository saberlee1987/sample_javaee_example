package com.saber.samplejavaee.controllers;

import com.saber.samplejavaee.model.query.Person;
import com.saber.samplejavaee.repositories.impl.PersonRepositoryImpl;
import com.saber.samplejavaee.services.PersonService;
import com.saber.samplejavaee.services.impl.PersonServiceImpl;

import javax.servlet.ServletConfig;
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
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = {"/person", "/person/*"}, name = "personController", displayName = "personController")
public class PersonController extends HttpServlet {

    private PersonService personService;
    private Validator validator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        personService = new PersonServiceImpl(new PersonRepositoryImpl());
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                showPersonData(request, response);
                break;
            case "detail":
                showPerson(request, response);
                break;
            case "delete":
                deletePersonById(request, response);
                break;
        }
    }

    private void deletePersonById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = getPersonIdFromRequest(request, response);
        if (id == null) {
            response.sendError(400, "Bad Request");
        } else {
            Boolean result = personService.deleteById(id);
            if (result) {
                response.sendRedirect(request.getContextPath() + "/person");
            } else {
                response.sendError(400, "can not delete Person,please call admin System");
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendError(400, "Bad Request");
        } else {
            addPerson(request, response);
        }

    }

    private void addPerson(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Person person = getPersonFromRequest(request);
        Set<ConstraintViolation<Person>> violations = validator.validate(person);
        if (violations.size() > 0) {
            request.setAttribute("violations", violations);
            request.setAttribute("person", person);
            showPersonData(request, response);
            return;
        }
        Boolean result = false;
        try {
            result = personService.save(person);
        } catch (Exception ex) {
            response.sendError(400, ex.getMessage());
            return;
        }
        if (result) {
            response.sendRedirect(request.getContextPath() + "/person");
        } else {
            response.sendError(400, "can not add new Person,please call admin System");
        }
    }

    private void showPerson(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = getPersonIdFromRequest(request, response);
        if (id == null) {
            response.sendError(400, "Bad Request");
        } else {
            Person person = personService.findById(id);
            request.setAttribute("person", person);
            request.getRequestDispatcher("/person-detail.jsp").forward(request, response);
        }
    }

    private void showPersonData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Person> persons = personService.findAll();
        request.setAttribute("persons", persons);
        request.getRequestDispatcher("/person.jsp").forward(request, response);
    }

    private Long getPersonIdFromRequest(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        requestURI = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        Long id = null;
        if (requestURI.matches("\\d+")) {
            id = Long.parseLong(requestURI);
        }
        return id;
    }

    private Person getPersonFromRequest(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        Integer age = null;
        if (request.getParameter("age") != null && request.getParameter("age").trim().length() > 0) {
            age = Integer.parseInt(request.getParameter("age"));
        }
        String email = request.getParameter("email");
        String nationalCode = request.getParameter("nationalCode");
        String mobile = request.getParameter("mobile");
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAge(age);
        person.setEmail(email);
        person.setNationalCode(nationalCode);
        person.setMobile(mobile);
        return person;
    }
}
