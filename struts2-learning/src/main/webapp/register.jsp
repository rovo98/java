<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: rovo98
  Date: 2018/4/4

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h3>Register for a prize by completing this form.</h3>
<s:form action="register">
    <s:textfield name="personBean.firstName" label="First name" />
    <s:textfield name="personBean.lastName" label="Last name" />
    <s:textfield name="personBean.email" label="Email" />
    <s:textfield name="personBean.age" label="Age" />
    <s:submit />
</s:form>
</body>
</html>
