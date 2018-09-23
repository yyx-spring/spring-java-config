<%--
  Created by IntelliJ IDEA.
  User: xiangzi
  Date: 2018/9/20
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
</head>
<body>
Hello
<hr>
<security:authentication property="principal.username" />
<hr>
<security:authentication property="details" />
<hr>
<security:authorize access="!hasRole('ROLE_ADMIN')">
not has role_admin
</security:authorize>
<br>
<a href="/signout">退出</a>
</body>
</html>