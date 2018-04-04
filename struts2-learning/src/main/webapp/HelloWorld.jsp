<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: rovo98
  Date: 2018/4/4
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
    <h2><s:property value="messageStore.message" /></h2>
    <p>I've said hello <s:property value="helloCount" /> times!</p>
    <!-- If we get the object here, the struts 2 will call the toString by default.-->
    <p><s:property value="messageStore" /></p>
    <p><a href="<s:url action='index'/>">Home Page</a></p>
</body>
</html>
