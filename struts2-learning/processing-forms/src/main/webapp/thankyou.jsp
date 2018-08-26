<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: rovo98
  Date: 2018/4/7
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Successful</title>
</head>
<body>
<h3>Thank you for registering for a prize.</h3>

<p>Your registration information: <s:property value="person"/></p>

<p><a href="<s:url action='index' />">Return to home page.</a></p>
</body>
</html>
