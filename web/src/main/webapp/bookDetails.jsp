<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/4.0.0-2/css/bootstrap.min.css" type="text/css"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" type="text/css">
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>
<div class="container mt-4">
    <div class="card mb-4 box-shadow h-md-250 mt-2">
        <div class="row m-3">
            <div class="col-md-3">
                <ul class="list-group list-group-flush" style="width: 220px">
                    <li class="list-group-item"><i class="fa fa-user"
                                                   aria-hidden="true"></i> ${requestScope.book.authorName}
                    </li>
                    <li class="list-group-item"><i class="fa fa-calendar"
                                                   aria-hidden="true"></i> ${requestScope.book.release}</li>
                    <li class="list-group-item"><i class="fa fa-tags"
                                                   aria-hidden="true"></i> ${requestScope.book.category}</li>
                    <li class="list-group-item"><i class="fa fa-id-card"
                                                   aria-hidden="true"></i> ${requestScope.book.isbn}</li>
                    <li class="list-group-item"><i class="fa fa-list-ol"
                                                   aria-hidden="true"></i> ${requestScope.book.pages}</li>
                </ul>
            </div>
            <div class="col-md-6">
                <strong class="d-inline-block mb-2 text-primary">
                    <c:choose>
                        <c:when test="${requestScope.book.borrow}">
                            <span class="badge badge-secondary mt-3">borrowed</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge badge-info mt-3">free</span>
                        </c:otherwise>
                    </c:choose>
                </strong>
                <h3 class="mb-0">
                    <a class="text-dark" href="#">${requestScope.book.title}</a>
                </h3>
                <p class="card-text">${requestScope.book.summary}</p>
                <c:choose>
                    <c:when test="${requestScope.book.borrow}">
                        <form action="BorrowServlet" method="get">
                            <button class="btn btn-outline-primary d-block" disabled>
                                Borrower: ${requestScope.book.borrowerName}</button>
                            <input type="hidden" name="bookId" value="${requestScope.book.id}">
                            <input type="hidden" name="borrowId" value="${requestScope.book.borrowId}">
                            <input type="submit" value="Return book" class="btn btn-danger mt-3">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="BorrowServlet" method="post">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="borrower">Borrower</label>
                                </div>
                                <select class="custom-select" id="borrower" name="borrowerId">
                                    <c:forEach var="borrower" items="${requestScope.borrowers}">
                                        <option value="${borrower.id}">${borrower.firstName} ${borrower.lastName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <input type="hidden" name="bookId" value="${requestScope.book.id}">
                            <input type="submit" value="Borrow" class="btn btn-primary mt-3">
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-md-3" style="text-align: center">
                <i class="fa fa-book book-icon" aria-hidden="true"></i>
            </div>
        </div>
    </div>
    <a href="HomeServlet">
        <button type="button" class="btn btn-danger">< Back to book list</button>
    </a>
    <jsp:include page="WEB-INF/fragments/footer.jsp"/>
</div>
</body>
</html>
