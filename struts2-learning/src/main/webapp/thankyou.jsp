<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: M shinoda
  Date: 2018/4/4
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registeration Successful</title>
</head>
<body>
<h3>Thank you for registering for a prize.</h3>

<p>Your registeration information: <s:property value="personBean"/></p>

<p><a href="<s:url action='index'/>">Return to home page</a></p>
</body>
</html>
