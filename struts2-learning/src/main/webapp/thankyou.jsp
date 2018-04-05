<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: rovo98
  Date: 2018/4/4

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registeration Successful</title>
</head>
<body>
<%--<h3>Thank you for registering for a prize.</h3>--%>
<!-- Using message resource -->
<h3><s:text name="thankyou" /></h3>
<p>Your registeration information: <s:property value="personBean"/></p>

<p><a href="<s:url action='index'/>">Return to home page</a></p>
</body>
</html>
