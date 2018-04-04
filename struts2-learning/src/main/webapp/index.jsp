<%--
  Created by IntelliJ IDEA.
  User: rovo98
  Date: 2018/4/4
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Basic Struts 2 Application - Welcome</title>
</head>
<body>
    <h1>Welcome to Struts 2!</h1>
    <p><a href="<s:url action='hello' /> ">Hello, World!</a></p>


    <!-- url tag with param -->
    <s:url action="hello" var="hellolink">
        <s:param name="userName">chester</s:param>
    </s:url>
    <p><a href="${ hellolink }">Hello chester!</a></p>
    <!-- Struts 2 Form Tag -->
    <p>Get your own personal hello by filling out and submitting this form.</p>
    <s:form action="hello">
        <s:textfield name="userName" label="Your name" />
        <s:submit value="Submit" />
    </s:form>
    <!-- Processing Form -->
    <p><a href="register.jsp">Please register</a> for our prize drawing.</p>
</body>
</html>
