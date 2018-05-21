package pl.sda.controller;

import pl.sda.dto.BookDto;
import pl.sda.model.Action;
import pl.sda.service.BookService;
import pl.sda.service.IBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

    private final IBookService bookService;

    public HomeServlet() {
        bookService = new BookService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Action action = Action.valueOf(request.getParameter("action"));
        Long bookId = Long.valueOf(request.getParameter("bookId"));

        switch (action) {
            case ADD:
                response.sendRedirect("/AddBookServlet");
                break;
            case EDIT:
                response.sendRedirect("/EditBookServlet?bookId=" + bookId);
                break;
            case SHOW:
                response.sendRedirect("/ShowBookDetailsServlet?bookId=" + bookId);
                break;
            case DELETE:
                bookService.delete(bookId);
                response.sendRedirect("/HomeServlet");
                break;
            default:
                throw new UnsupportedOperationException("no action");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookDto> books = bookService.findAll();
        request.setAttribute("books", books);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
