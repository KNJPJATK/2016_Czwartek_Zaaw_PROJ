<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<!--<% //System.out.println("abc"); %> !!!!!!! NIE ROBIC!!!!!!!!!-->

<%--<jsp:useBean id="user" class="pl.knpj.servlet.model.User"/>--%>

<%--${userBean.username}--%>

    <c:if test="${user != null}" >
        <h1>SUPER :D</h1>
    </c:if>

    <c:forEach begin="0" end="1" step="1" var="abc">
        <h4>${abc}</h4>
    </c:forEach>

    <c:forEach items="${quizList}" var="quiz" varStatus="status" >
        ${status.count} <strong>${quiz}</strong> <br>
    </c:forEach>

    ${quizList.get(0)}
    <div class="${quizList}">
        <c:choose>
            <c:when test='${quizList.get(0) == "quiz1"}'>
                <h1>Jeden</h1>
            </c:when>
        <c:otherwise><h1>Otherwise</h1></c:otherwise>
    </c:choose>
    </div>

</body>
</html>
