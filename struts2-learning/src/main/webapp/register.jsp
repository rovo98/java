<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: rovo98
  Date: 2018/4/4

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Register</title>
    <s:head />
</head>
<body>
<s:text name="greeting" />
<s:text name="instructions" />
<s:form action="register">
    <%--<s:textfield name="personBean.firstName" label="First name" />--%>
    <%--<s:textfield name="personBean.lastName" label="Last name" />--%>
    <%--<s:textfield name="personBean.email" label="Email" />--%>
    <%--<s:textfield name="personBean.age" label="Age" />--%>
    <!-- Using message resource here -->
    <s:textfield key="personBean.firstName" />
    <s:textfield key="personBean.lastName" />
    <s:textfield key="personBean.email" />
    <s:textfield key="personBean.age" />
    <s:submit key="submit"/>
</s:form>
</body>
</html>
