<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: rovo98
  Date: 2018/4/7
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World!</title>
</head>
<body>
<h2><s:property value="messageStore.message" /></h2>

<p>I've said hello <s:property value="helloCount"/> times!</p>

<p><s:property value="messageStore"/></p>
<p><a href="<s:url action='index' />">Return to home page.</a></p>
</body>
</html>
