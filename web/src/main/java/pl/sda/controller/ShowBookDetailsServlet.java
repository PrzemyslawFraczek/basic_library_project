package pl.sda.controller;

import pl.sda.exception.ItemNotFoundException;
import pl.sda.service.BookService;
import pl.sda.service.BorrowerService;
import pl.sda.service.IBookService;
import pl.sda.service.IBorrowerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ShowBookDetailsServlet")
public class ShowBookDetailsServlet extends HttpServlet {

    private final IBookService bookService;
    private final IBorrowerService borrowerService;

    public ShowBookDetailsServlet() {
        borrowerService = new BorrowerService();
        bookService = new BookService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long bookId = Long.valueOf(request.getParameter("bookId"));
        try {
            request.setAttribute("book", bookService.find(bookId));
            request.setAttribute("borrowers", borrowerService.findAll());
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
    }
}
