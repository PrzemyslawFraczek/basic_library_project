<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/4.0.0-2/css/bootstrap.min.css" type="text/css"
          rel="stylesheet">
</head>
<body>
<form action="/AuthorServlet" method="post">
    <div class="form-group">
        <label>Firstname</label>
        <input type="text" class="form-control" name="firstname">
    </div>
    <div class="form-group">
        <label>Lastname</label>
        <input type="text" class="form-control" name="lastname">
    </div>
    <button type="submit" class="btn btn-primary">Add</button>
</form>
</body>
</html>
