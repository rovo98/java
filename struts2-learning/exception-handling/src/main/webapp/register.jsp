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

<s:form action="com.rovo98.struts.register">
    <s:textfield key="person.firstName"/>
    <s:textfield key="person.lastName" />
    <s:textfield key="person.email" />
    <s:textfield key="person.age" />
    <s:submit value="%{getText('submit')}" />
</s:form>
</body>
</html>
