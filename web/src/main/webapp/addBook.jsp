<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/4.0.0-2/css/bootstrap.min.css" type="text/css"
          rel="stylesheet">
</head>
<body>
<jsp:include page="WEB-INF/fragments/header.jsp"/>
<div class="container mt-4">
    <form action="/AddBookServlet" method="post">
        <jsp:include page="WEB-INF/fragments/form.jsp"/>
        <input type="hidden" name="authorId" value="${requestScope.authorId}">
        <input type="submit" value="Add" class="btn btn-success">
        <a href="/HomeServlet">
            <button type="button" class="btn btn-outline-secondary">Cancel</button>
        </a>
    </form>
    <jsp:include page="WEB-INF/fragments/footer.jsp"/>
</div>
</body>
</html>
