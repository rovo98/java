<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: rovo98
  Date: 2018/4/10

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Successful</title>
</head>
<body>
<p>Your Update information: <s:property value="personBean"/></p>

<p><a href="<s:url action='index'/>">Return to home page</a></p>
</body>
</html>
