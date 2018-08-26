<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: rovo98
  Date: 2018/4/7
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Register</title>
    <s:head />
</head>
<body>
<h3>Register for a prize by completing this form.</h3>

<s:form action="register">
    <s:textfield name="person.firstName" label="First name" />
    <s:textfield name="person.lastName" label="Last name"/>
    <s:textfield name="person.email" label="Email"/>
    <s:textfield name="person.age" label="Age"/>
    <s:submit value="Register"/>
</s:form>
</body>
</html>
