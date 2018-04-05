<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: rovo98
  Date: 2018/4/5
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exception Handling - Error</title>
</head>
<body>
<h4>The application has malfunctioned.</h4>
<p>Please contact technical support with the following information:</p>

<h4>Exception Name: <s:property value="exception"/></h4>
<h4>Exception Details: <s:property value="exceptionStack"/></h4>
</body>
</html>
