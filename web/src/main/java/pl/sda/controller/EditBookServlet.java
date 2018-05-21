package pl.sda.controller;

import pl.sda.dto.BookDto;
import pl.sda.exception.ItemNotFoundException;
import pl.sda.model.BooksType;
import pl.sda.service.BookService;
import pl.sda.service.IBookService;
import pl.sda.util.ErrorUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@WebServlet("/EditBookServlet")
public class EditBookServlet extends HttpServlet {

    private final IBookService bookService;

    public EditBookServlet() {
        bookService = new BookService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDto bookDto = createBookDto(request);
        List<String> errors = ErrorUtil.getErrors(bookDto);
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("categories", bookService.findAllCategories());
            request.setAttribute("book", bookDto);
            request.getRequestDispatcher("editBook.jsp").forward(request, response);
        }
        try {
            bookService.edit(bookDto);
        } catch (ItemNotFoundException e) {
            request.setAttribute("errors", Collections.singletonList(e.getMessage()));
        }
        response.sendRedirect("HomeServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long bookId = Long.valueOf(request.getParameter("bookId"));
        BookDto bookDto = null;
        try {
            bookDto = bookService.find(bookId);
        } catch (ItemNotFoundException e) {
            request.setAttribute("errors", Collections.singletonList(e.getMessage()));
        }
       request.setAttribute("book", bookDto);
        request.setAttribute("categories", bookService.findAllCategories());
        request.getRequestDispatcher("editBook.jsp").forward(request, response);
    }

    private BookDto createBookDto(HttpServletRequest request) {
        BookDto dto = new BookDto();
        dto.setId(Long.valueOf(request.getParameter("id")));
        dto.setSummary(request.getParameter("summary"));
        dto.setPages(Objects.isNull(request.getParameter("pages"))
                || request.getParameter("pages").equals("") ? 0 : Integer.valueOf(request.getParameter("pages")));
        dto.setIsbn(request.getParameter("isbn"));
        dto.setTitle(request.getParameter("title"));
        dto.setRelease(Objects.isNull(request.getParameter("release"))
                || request.getParameter("release").equals("") ? null : LocalDate.parse(request.getParameter("release")));
        dto.setAuthorId(Long.valueOf(request.getParameter("authorId")));
        dto.setCategory(BooksType.valueOf(request.getParameter("category")));
        return dto;
    }
}
