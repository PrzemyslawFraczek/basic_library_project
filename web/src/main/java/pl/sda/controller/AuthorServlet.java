package pl.sda.controller;

import pl.sda.model.Author;
import pl.sda.service.AuthorService;
import pl.sda.service.IAuthorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AuthorServlet")
public class AuthorServlet extends HttpServlet {

    private final IAuthorService authorService;

    public AuthorServlet() {
        authorService = new AuthorService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        Author author = new Author();
        author.setFirstName(firstname);
        author.setLastName(lastname);
        authorService.saveAuthor(author);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
