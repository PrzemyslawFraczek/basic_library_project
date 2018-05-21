package pl.sda.controller;

import pl.sda.dto.BorrowDto;
import pl.sda.exception.BorrowNotFoundException;
import pl.sda.service.BorrowService;
import pl.sda.service.IBorrowService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BorrowServlet")
public class BorrowServlet extends HttpServlet {

    private final IBorrowService borrowService;

    public BorrowServlet() {
        borrowService = new BorrowService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BorrowDto borrowDto = createBorrowDto(request);
        try {
            borrowService.save(borrowDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("ShowBookDetailsServlet?bookId=" + borrowDto.getBookId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long borrowId = Long.valueOf(request.getParameter("borrowId"));
        Long bookId = Long.valueOf(request.getParameter("bookId"));
        try {
            borrowService.delete(borrowId);
        } catch (BorrowNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("ShowBookDetailsServlet?bookId=" + bookId);
    }

    private BorrowDto createBorrowDto(HttpServletRequest request) {
        BorrowDto dto = new BorrowDto();
        dto.setBorrowerId(Long.valueOf(request.getParameter("borrowerId")));
        dto.setBookId(Long.valueOf(request.getParameter("bookId")));
        return dto;
    }
}
