<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="error" items="${requestScope.errors}">
    <div class="alert alert-danger mb-1" role="alert">
        <c:out value="${error}"/>
    </div>
</c:forEach>
<div class="row">
    <div class="col-sm-6">
        <div class="form-group">
            <label for="bookTitle">Title</label>
            <input type="text" class="form-control" id="bookTitle" name="title" value="${requestScope.book.title}">
        </div>
        <div class="form-group">
            <label for="bookIsbn">ISBN</label>
            <input type="text" class="form-control" id="bookIsbn" name="isbn" value="${requestScope.book.isbn}">
        </div>
        <div class="form-group">
            <label for="bookPages">Pages</label>
            <input type="number" class="form-control" id="bookPages" name="pages" value="${requestScope.book.pages}">
        </div>
    </div>
    <div class="col-sm-6">
        <div class="form-group">
            <label for="bookCategory">Category</label>
            <select class="form-control" id="bookCategory" name="category">
                <c:forEach var="category" items="${requestScope.categories }">
                    <option>${category}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="bookRelease">Release</label>
            <input type="date" class="form-control" id="bookRelease" name="release" value="${requestScope.book.release}">
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-12">
        <div class="form-group">
            <label for="bookSummary">Summary</label>
            <textarea rows="3"
                      class="form-control"
                      id="bookSummary"
                      name="summary">${requestScope.book.summary}</textarea>
        </div>
    </div>
</div>