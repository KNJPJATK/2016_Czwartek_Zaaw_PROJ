<%@ page contentType="text/html; UTF-8" language="java" %>
<html>
<body>

    <jsp:useBean id="user" class="pl.knpj.servlet.model.User" scope="session" />
    <h1>Witaj ${user.username}</h1>



</body>
</html>
