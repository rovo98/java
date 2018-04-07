<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: rovo98
  Date: 2018/4/7
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basic Struts 2 Application - Welcome</title>
</head>
<body>
<h1>Welcome To Struts 2!</h1>

<p><a href="<s:url action='hello' />">Hello World</a></p>

<s:url action="hello" var="helloLink">
    <s:param name="userName">rovo98</s:param>
</s:url>
<p><a href="${helloLink}">Hello rovo98</a></p>

<p>Get your own personal hell by filling out and submitting this form.</p>
<s:form action="hello">
    <s:textfield name="userName" label="Your name" />
    <s:submit value="Submit"/>
</s:form>
</body>
</html>
